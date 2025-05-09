import boto3
import os
import time
import json  # Added this import to fix the issue

# === CONFIG ===
AMI_ID = "ami-0cda377a1b884a1bc"  # Ubuntu 22.04 LTS (ap-south-1)
INSTANCE_TYPE = "t2.micro"
KEY_NAME = "fastapi-key"
SECURITY_GROUP_NAME = "fastapi-sg"
REGION = "ap-south-1"
S3_BUCKET = "ai-image-recognition-0eb33243"
DYNAMO_TABLE = "ImageLabels"
PEM_FILE = f"{KEY_NAME}.pem"

ec2 = boto3.resource("ec2", region_name=REGION)
client = boto3.client("ec2", region_name=REGION)
s3 = boto3.client("s3", region_name=REGION)
dynamodb = boto3.client("dynamodb", region_name=REGION)
iam = boto3.client("iam", region_name=REGION)

ROLE_NAME = "EC2ImageProcessorRole"
INSTANCE_PROFILE_NAME = "EC2ImageProcessorProfile"
ROLE_POLICIES = [
    "arn:aws:iam::aws:policy/AmazonS3FullAccess",
    "arn:aws:iam::aws:policy/AmazonDynamoDBFullAccess"
]

def create_s3_bucket():
    try:
        s3.head_bucket(Bucket=S3_BUCKET)
        print(f"[🪣] S3 bucket '{S3_BUCKET}' already exists.")
    except:
        s3.create_bucket(
            Bucket=S3_BUCKET,
            CreateBucketConfiguration={"LocationConstraint": REGION}
        )
        print(f"[🪣] Created S3 bucket: {S3_BUCKET}")

def create_dynamodb_table():
    try:
        tables = dynamodb.list_tables()["TableNames"]
        if DYNAMO_TABLE in tables:
            print(f"[📦] DynamoDB table '{DYNAMO_TABLE}' already exists.")
            return
        dynamodb.create_table(
            TableName=DYNAMO_TABLE,
            KeySchema=[{"AttributeName": "filename", "KeyType": "HASH"}],
            AttributeDefinitions=[{"AttributeName": "filename", "AttributeType": "S"}],
            ProvisionedThroughput={"ReadCapacityUnits": 5, "WriteCapacityUnits": 5}
        )
        print(f"[📦] Creating DynamoDB table '{DYNAMO_TABLE}'...")
        waiter = dynamodb.get_waiter('table_exists')
        waiter.wait(TableName=DYNAMO_TABLE)
        print(f"[✅] DynamoDB table is now active.")
    except Exception as e:
        print(f"[❌] Error creating DynamoDB table: {e}")

def create_iam_role():
    try:
        print("[🔧] Creating IAM role...")
        assume_role_policy = {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Effect": "Allow",
                    "Principal": {"Service": "ec2.amazonaws.com"},
                    "Action": "sts:AssumeRole"
                }
            ]
        }

        iam.create_role(
            RoleName=ROLE_NAME,
            AssumeRolePolicyDocument=json.dumps(assume_role_policy),
            Description="Allows EC2 to access S3 and DynamoDB"
        )

        for policy in ROLE_POLICIES:
            iam.attach_role_policy(RoleName=ROLE_NAME, PolicyArn=policy)

        print("[✅] IAM role created and policies attached.")

    except iam.exceptions.EntityAlreadyExistsException:
        print(f"[!] IAM role '{ROLE_NAME}' already exists. Skipping creation.")

    # Create instance profile if not exists
    profiles = iam.list_instance_profiles()["InstanceProfiles"]
    profile_names = [p["InstanceProfileName"] for p in profiles]

    if INSTANCE_PROFILE_NAME not in profile_names:
        print(f"[🔗] Instance profile '{INSTANCE_PROFILE_NAME}' does not exist, creating it...")
        iam.create_instance_profile(InstanceProfileName=INSTANCE_PROFILE_NAME)
        iam.add_role_to_instance_profile(InstanceProfileName=INSTANCE_PROFILE_NAME, RoleName=ROLE_NAME)
        print(f"[🔗] Created and attached instance profile '{INSTANCE_PROFILE_NAME}'")
    else:
        print(f"[!] Instance profile '{INSTANCE_PROFILE_NAME}' already exists.")

    # Check and associate instance profile with the running EC2 instance
    try:
        print("[🔍] Checking for EC2 instance to associate IAM profile...")
        instance_id = get_running_instance_id()
        print(f"[🔗] Found running instance: {instance_id}")

        response = client.describe_iam_instance_profile_associations(
            Filters=[{"Name": "instance-id", "Values": [instance_id]}]
        )

        if not response["IamInstanceProfileAssociations"]:
            print("[🔗] No IAM profile attached. Associating now...")
            client.associate_iam_instance_profile(
                IamInstanceProfile={"Name": INSTANCE_PROFILE_NAME},
                InstanceId=instance_id
            )
            print(f"[✅] Instance profile '{INSTANCE_PROFILE_NAME}' attached to instance '{instance_id}'.")
        else:
            print("[✅] Instance already has an IAM profile.")

    except Exception as e:
        print(f"[⚠️] Could not attach IAM profile: {e}")

def create_key_pair():
    try:
        response = client.create_key_pair(KeyName=KEY_NAME)
        with open(PEM_FILE, "w") as file:
            file.write(response["KeyMaterial"])
        os.chmod(PEM_FILE, 0o400)
        print(f"[🔐] Key pair '{KEY_NAME}' created and saved as '{PEM_FILE}'")
    except client.exceptions.ClientError as e:
        if "InvalidKeyPair.Duplicate" in str(e):
            print(f"[!] Key pair '{KEY_NAME}' already exists.")
        else:
            raise e

def create_security_group():
    try:
        vpc_id = list(ec2.vpcs.limit(1))[0].id
        response = client.create_security_group(
            GroupName=SECURITY_GROUP_NAME,
            Description="Allow FastAPI access",
            VpcId=vpc_id
        )
        sg_id = response["GroupId"]
        client.authorize_security_group_ingress(
            GroupId=sg_id,
            IpPermissions=[
                {"IpProtocol": "tcp", "FromPort": 22, "ToPort": 22,
                 "IpRanges": [{"CidrIp": "0.0.0.0/0"}]},
                {"IpProtocol": "tcp", "FromPort": 8000, "ToPort": 8000,
                 "IpRanges": [{"CidrIp": "0.0.0.0/0"}]}
            ]
        )
        print(f"[🛡️] Created security group '{SECURITY_GROUP_NAME}'")
        return sg_id
    except client.exceptions.ClientError as e:
        if "InvalidGroup.Duplicate" in str(e):
            sg = client.describe_security_groups(GroupNames=[SECURITY_GROUP_NAME])['SecurityGroups'][0]
            print(f"[!] Reusing existing security group '{SECURITY_GROUP_NAME}'")
            return sg["GroupId"]
        else:
            raise e

def get_user_data_script():
    return f'''#!/bin/bash
sudo apt update -y
sudo apt install python3-pip -y
pip3 install fastapi boto3 uvicorn python-multipart

mkdir -p /home/ubuntu/fastapi-backend/uploads
cd /home/ubuntu/fastapi-backend

cat > app.py <<EOF
from fastapi import FastAPI, File, UploadFile
import boto3, uuid, os, time
from botocore.exceptions import NoCredentialsError

app = FastAPI()
S3_BUCKET = "{S3_BUCKET}"
REGION = "{REGION}"

# Automatically use IAM role credentials
session = boto3.Session()
s3 = session.client("s3", region_name=REGION)
dynamodb = session.resource("dynamodb", region_name=REGION)
table = dynamodb.Table("{DYNAMO_TABLE}")

@app.post("/upload/")
async def upload_image(file: UploadFile = File(...)):
    try:
        ext = file.filename.split(".")[-1]
        filename = f"{{uuid.uuid4().hex}}.{{ext}}"
        local_path = f"uploads/{{filename}}"

        with open(local_path, "wb") as f:
            f.write(await file.read())

        s3.upload_file(local_path, S3_BUCKET, filename)
        os.remove(local_path)

        labels = None
        for _ in range(10):
            time.sleep(1)
            response = table.get_item(Key={{"filename": filename}})
            if "Item" in response:
                labels = response["Item"]["labels"]
                break

        return {{
            "message": "Image uploaded and processed",
            "filename": filename,
            "labels": labels if labels else "Processing... try again soon"
        }}

    except NoCredentialsError:
        return {{"error": "AWS credentials not found. Make sure IAM role is attached."}}
    except Exception as e:
        return {{"error": str(e)}}

@app.get("/aws-check")
def check():
    return {{"message": "App is running and credentials are set!"}}
EOF

nohup uvicorn app:app --host 0.0.0.0 --port 8000 &
'''

def get_running_instance_id():
    response = client.describe_instances(
        Filters=[
            {"Name": "tag:Name", "Values": ["FastAPI-Backend"]},
            {"Name": "instance-state-name", "Values": ["running"]}
        ]
    )
    reservations = response.get("Reservations", [])
    if not reservations:
        raise Exception("[❌] No running EC2 instance found with name 'FastAPI-Backend'")
    return reservations[0]["Instances"][0]["InstanceId"]


def launch_instance():
    sg_id = create_security_group()
    user_data = get_user_data_script()

    # Make sure IAM role exists
    create_iam_role()

    instance = ec2.create_instances(
        ImageId=AMI_ID,
        InstanceType=INSTANCE_TYPE,
        KeyName=KEY_NAME,
        MinCount=1,
        MaxCount=1,
        SecurityGroupIds=[sg_id],
        UserData=user_data,
        IamInstanceProfile={"Name": INSTANCE_PROFILE_NAME},
        TagSpecifications=[{
            "ResourceType": "instance",
            "Tags": [{"Key": "Name", "Value": "FastAPI-Backend"}]
        }]
    )[0]

    print("[🚀] Launching EC2 instance...")
    instance.wait_until_running()
    instance.reload()

    public_ip = instance.public_ip_address
    print(f"[✅] Server is live at: http://{public_ip}:8000/upload/")
    print(f"[🔐] Connect via: ssh -i {PEM_FILE} ubuntu@{public_ip}")
    return public_ip

if __name__ == "__main__":
    create_s3_bucket()
    create_dynamodb_table()
    create_key_pair()
    launch_instance()

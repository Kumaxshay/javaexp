import boto3
import json

def create_iam_role(role_name="AIRecognitionRole"):
    iam_client = boto3.client("iam")

    assume_role_policy = {
        "Version": "2012-10-17",
        "Statement": [
            {
                "Effect": "Allow",
                "Principal": {"Service": "lambda.amazonaws.com"},
                "Action": "sts:AssumeRole"
            }
        ]
    }

    # Create IAM Role
    role = iam_client.create_role(
        RoleName=role_name,
        AssumeRolePolicyDocument=json.dumps(assume_role_policy),
        Description="IAM Role for Lambda to access S3 and Rekognition"
    )

    print(f"IAM Role '{role_name}' created successfully.")
    return role["Role"]["Arn"]

if __name__ == "__main__":
    role_arn = create_iam_role()
    print(f"Role ARN: {role_arn}")

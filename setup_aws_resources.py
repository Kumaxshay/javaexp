import boto3
import uuid

def create_s3_bucket(region="ap-south-1"):
    s3_client = boto3.client("s3", region_name=region)
    
    # Generate a unique bucket name
    unique_id = str(uuid.uuid4())[:8]  # Get first 8 characters of a UUID
    bucket_name = f"ai-image-recognition-{unique_id}"
    
    s3_client.create_bucket(
        Bucket=bucket_name,
        CreateBucketConfiguration={"LocationConstraint": region}
    )

    print(f"Bucket '{bucket_name}' created successfully.")

if __name__ == "__main__":
    create_s3_bucket()

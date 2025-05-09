import boto3

def upload_file_to_s3(file_name, bucket_name, object_name=None):
    s3_client = boto3.client("s3")

    if object_name is None:
        object_name = file_name  # Use the file name as the object name

    try:
        s3_client.upload_file(file_name, bucket_name, object_name)
        print(f"Uploaded {file_name} to s3://{bucket_name}/{object_name}")
    except Exception as e:
        print(f"Error uploading file: {e}")

if __name__ == "__main__":
    BUCKET_NAME = "ai-image-recognition-0eb33243"  
    FILE_NAME = "IMG.jpg"
    upload_file_to_s3(FILE_NAME, BUCKET_NAME)

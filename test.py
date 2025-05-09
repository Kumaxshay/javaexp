import boto3
import json

s3 = boto3.client("s3")
bucket_name = "ai-image-recognition-a0bed4a4"

response = s3.get_bucket_notification_configuration(Bucket=bucket_name)
print(json.dumps(response, indent=2))

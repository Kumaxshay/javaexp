import boto3
import json

def lambda_handler(event, context):
    rekognition_client = boto3.client("rekognition")

    # Extract bucket name and image file name from S3 event
    bucket_name = event['Records'][0]['s3']['bucket']['name']
    file_name = event['Records'][0]['s3']['object']['key']

    # Call Rekognition to detect labels in the image
    response = rekognition_client.detect_labels(
        Image={'S3Object': {'Bucket': bucket_name, 'Name': file_name}},
        MaxLabels=5
    )

    # Extract label names and confidence scores
    labels = [{"Name": label["Name"], "Confidence": label["Confidence"]} for label in response["Labels"]]

    print(f"Detected labels for {file_name}: {labels}")

    return {
        "statusCode": 200,
        "body": json.dumps(labels)
    }

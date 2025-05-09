import boto3
import json

def allow_s3_to_invoke_lambda(lambda_name, bucket_name):
    import hashlib
    lambda_client = boto3.client("lambda")
    
    # Create a unique statement ID from bucket name
    statement_id = f"S3InvokeLambda_{hashlib.md5(bucket_name.encode()).hexdigest()[:8]}"

    try:
        response = lambda_client.add_permission(
            FunctionName=lambda_name,
            StatementId=statement_id,
            Action="lambda:InvokeFunction",
            Principal="s3.amazonaws.com",
            SourceArn=f"arn:aws:s3:::{bucket_name}"
        )
        print("Lambda invoke permission added.")
    except lambda_client.exceptions.ResourceConflictException:
        print("Permission already exists, skipping.")


def add_s3_trigger(bucket_name, lambda_function_arn):
    s3_client = boto3.client("s3")

    notification_configuration = {
        "LambdaFunctionConfigurations": [
            {
                "LambdaFunctionArn": lambda_function_arn,
                "Events": ["s3:ObjectCreated:*"]
            }
        ]
    }

    response = s3_client.put_bucket_notification_configuration(
        Bucket=bucket_name,
        NotificationConfiguration=notification_configuration
    )

    print(f"S3 trigger added successfully.")

if __name__ == "__main__":
    BUCKET_NAME = "ai-image-recognition-0eb33243"  # Replace with your actual S3 bucket name
    LAMBDA_FUNCTION_NAME = "ImageRecognitionLambda"  # Replace with your actual Lambda function name
    LAMBDA_ARN = "arn:aws:lambda:ap-south-1:442042533558:function:ImageRecognitionLambda"  # Replace with your actual Lambda ARN

    # Step 1: Allow S3 to invoke Lambda
    allow_s3_to_invoke_lambda(LAMBDA_FUNCTION_NAME, BUCKET_NAME)

    # Step 2: Add S3 trigger
    add_s3_trigger(BUCKET_NAME, LAMBDA_ARN)

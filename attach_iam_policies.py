import boto3

def attach_policies(role_name="AIRecognitionRole"):
    iam_client = boto3.client("iam")

    # Attach AmazonS3FullAccess policy
    try:
        iam_client.attach_role_policy(
            RoleName=role_name,
            PolicyArn="arn:aws:iam::aws:policy/AmazonS3FullAccess"
        )
        print(f"AmazonS3FullAccess policy attached successfully.")
    except Exception as e:
        print(f"Error attaching AmazonS3FullAccess: {e}")

    # Attach AmazonRekognitionFullAccess policy
    try:
        iam_client.attach_role_policy(
            RoleName=role_name,
            PolicyArn="arn:aws:iam::aws:policy/AmazonRekognitionFullAccess"
        )
        print(f"AmazonRekognitionFullAccess policy attached successfully.")
    except Exception as e:
        print(f"Error attaching AmazonRekognitionFullAccess: {e}")

    # Attach AWSLambdaBasicExecutionRole policy
    try:
        iam_client.attach_role_policy(
            RoleName=role_name,
            PolicyArn="arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
        )
        print(f"AWSLambdaBasicExecutionRole policy attached successfully.")
    except Exception as e:
        print(f"Error attaching AWSLambdaBasicExecutionRole: {e}")

    # Attach AmazonDynamoDBFullAccess policy (for DynamoDB permissions)
    try:
        iam_client.attach_role_policy(
            RoleName=role_name,
            PolicyArn="arn:aws:iam::aws:policy/AmazonDynamoDBFullAccess"
        )
        print(f"AmazonDynamoDBFullAccess policy attached successfully.")
    except Exception as e:
        print(f"Error attaching AmazonDynamoDBFullAccess: {e}")
    
    print(f"Policies attached to IAM Role '{role_name}' successfully.")

if __name__ == "__main__":
    attach_policies()

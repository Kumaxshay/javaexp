import boto3

def create_lambda_function(function_name="ImageRecognitionLambda", role_arn=""):
    lambda_client = boto3.client("lambda")

    with open("lambda_function.zip", "rb") as f:
        zip_content = f.read()

    response = lambda_client.create_function(
        FunctionName=function_name,
        Runtime="python3.12",
        Role=role_arn,
        Handler="lambda_function.lambda_handler",
        Code={"ZipFile": zip_content},
        Timeout=10,
        MemorySize=128
    )

    print(f"Lambda function '{function_name}' created successfully.")
    return response

if __name__ == "__main__":
    ROLE_ARN = "arn:aws:iam::442042533558:role/AIRecognitionRole"   
    create_lambda_function(role_arn=ROLE_ARN)

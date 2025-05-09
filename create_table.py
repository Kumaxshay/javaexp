import boto3

# ✅ Use the same region where your Lambda runs
dynamodb = boto3.client("dynamodb", region_name="ap-south-1")

table_name = "ImageLabels"

try:
    response = dynamodb.create_table(
        TableName=table_name,
        KeySchema=[
            {
                'AttributeName': 'filename',
                'KeyType': 'HASH'  # Partition key
            }
        ],
        AttributeDefinitions=[
            {
                'AttributeName': 'filename',
                'AttributeType': 'S'
            }
        ],
        ProvisionedThroughput={
            'ReadCapacityUnits': 5,
            'WriteCapacityUnits': 5
        }
    )

    print(f"✅ Table '{table_name}' is being created...")

except dynamodb.exceptions.ResourceInUseException:
    print(f"⚠️ Table '{table_name}' already exists.")

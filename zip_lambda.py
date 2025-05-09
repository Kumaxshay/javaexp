import zipfile

def create_lambda_zip():
    with zipfile.ZipFile("lambda_function.zip", "w") as zipf:
        zipf.write("lambda_function.py")

    print("Lambda function zipped successfully.")

if __name__ == "__main__":
    create_lambda_zip()

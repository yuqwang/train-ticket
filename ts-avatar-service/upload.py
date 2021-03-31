import oss2
import os
import sys
import time

endpoint = os.getenv("OSS_ENDPOINT")
auth_key = os.getenv("OSS_AUTH_KEY")
auth_secret = os.getenv("OSS_AUTH_SECRET")
bucket_name = os.getenv("OSS_BUCKET_NAME")
bucket_address = os.getenv("OSS_BUCKET_ADDRESS")

if endpoint is None or auth_key is None or auth_secret is None or bucket_name is None or bucket_address is None:
    print(f"endpoint: {endpoint}, auth_key: {auth_key}, auth_secret: {auth_secret}, bucket_name: {bucket_name}, "
          f"bucket_address: {bucket_address}. something is not set in env.")
    sys.exit(1)

auth = oss2.Auth(auth_key, auth_secret)
bucket = oss2.Bucket(auth, endpoint, bucket_name)


def upload_file(file_path):
    file_type = file_path.split(".")[-1]
    file_name = str(time.time()) + "." + file_type

    bucket.put_object_from_file(file_name, file_path)

    address = bucket_address + file_name
    return address
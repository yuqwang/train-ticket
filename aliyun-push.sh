#!/bin/bash
docker login --username=xxxxx@foxmail.com --password=xxxxx registry.cn-shanghai.aliyuncs.com

repo_address="registry.cn-shanghai.aliyuncs.com/xywu" # add repo address
version="error.F4"                               # add version
#version="log.error"                               # add version

# get all the service
all_service=($(ls -d ts-*-service) "ts-common" "ts-ui-dashboard")
delete=("ts-news-service" "ts-ticket-office-service" "ts-voucher-service")
for target in "${delete[@]}"; do
  for i in "${!all_service[@]}"; do
    if [[ ${all_service[i]} == $target ]]; then
      unset 'all_service[i]'
    fi
  done
done

# init service
service=()
if [ $# -eq 0 ]; then
  service=(${all_service[@]})
else
  for target in $@; do
    for i in "${!all_service[@]}"; do
      if [[ ${all_service[i]} == $target ]]; then
        service=(${service[@]} $target)
        unset 'all_service[i]'
      fi
    done
  done
fi

root=$(pwd)
# build and push image
for service_name in ${service[@]}; do
  image_name=${service_name}:${version}
  image_push_address=${repo_address}/${image_name}

  dir_path=${root}/${service_name}
  cd $dir_path
  pwd

  if [[ ${all_service[i]} != "ts-ui-dashboard" ]]; then
    echo "ts-ui-dashboard"
  else
    mvn clean package -Dmaven.test.skip=true
  fi

  docker build -t ${image_name} .

  if test ! -z "$(docker images | grep ${image_name})"; then
    docker rmi ${image_push_address}
  fi

  docker tag ${image_name} ${image_push_address}
  docker push ${image_push_address}
  echo ${image_push_address}
done

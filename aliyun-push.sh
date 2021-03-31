docker login --username=xiya.wu@foxmail.com --password=Hello=111! registry.cn-shanghai.aliyuncs.com
repo_address="registry.cn-shanghai.aliyuncs.com/xywu"


service="ts-route-service"
mytag=""

cd $service
pwd
mvn clean package
docker build -t ${service}${mytag} .

docker rmi ${repo_address}/${service}
docker tag ${service}${mytag} ${repo_address}/${service}${mytag}
docker push ${repo_address}/${service}${mytag}
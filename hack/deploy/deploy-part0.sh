#!/bin/bash
function deploy_part0 {
  namespace=$1
  echo "Start deployment Step <1/3>----------------------"
  echo "Start to deploy mysql cluster for nacos."
  #kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part0/nacos-rdb.yaml
  helm install nacos --set mysql.mysqlUser=nacos --set mysql.mysqlPassword="Abcd1234#" --set mysql.mysqlDatabase=nacos deployment/kubernetes-manifests/quickstart-k8s/charts/mysql -n $namespace
  echo "Waiting for mysql cluster of nacos to be ready ......"
  kubectl rollout status statefulset/nacos-mysql -n $namespace
  echo "Start to deploy nacos."
  #kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part0/nacos.yaml
  helm install nacos --set nacos.db.host=nacos-mysql-leader --set nacos.db.username=nacos --set nacos.db.name=nacos --set nacos.db.password="Abcd1234#" deployment/kubernetes-manifests/quickstart-k8s/charts/nacos -n $namespace
  echo "Waiting for nacos to be ready ......"
  kubectl rollout status statefulset/nacos -n $namespace
  echo "Start to deploy rabbitmq."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part0/rabbitmq.yaml -n $namespace
  echo "Waiting for rabbitmq to be ready ......"
  kubectl rollout status deployment/rabbitmq -n $namespace
  echo "End deployment Step <1/3>----------------------"
}

function deploy_monitoring {
  echo "Start deploy promethues and grafana"
  kubectl apply -f deployment/kubernetes-manifests/promethues
}

function deploy_tracing {
  echo "Start deploy skywalking"
  kubectl apply -f deployment/kubernetes-manifests/skywalking -n $namespace
}



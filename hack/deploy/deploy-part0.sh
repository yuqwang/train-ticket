#!/bin/bash
function deploy_part0 {
  echo "Start deployment Step <1/3>----------------------"
  echo "Start to deploy mysql cluster for nacos."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part0/nacos-rdb.yaml
  echo "Waiting for mysql cluster of nacos to be ready ......"
  kubectl rollout status statefulset/nacos-rdb
  echo "Start to deploy nacos."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part0/nacos.yaml
  echo "Waiting for nacos to be ready ......"
  kubectl rollout status statefulset/nacos
  echo "Start to deploy rabbitmq."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part0/rabbitmq.yaml
  echo "Waiting for rabbitmq to be ready ......"
  kubectl rollout status deployment/rabbitmq
  echo "End deployment Step <1/3>----------------------"
}



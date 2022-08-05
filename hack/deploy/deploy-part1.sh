#!/bin/bash

TT_ROOT=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source "$TT_ROOT/gen_mysql.sh"

function deploy_tt_mysql_all_in_one {
  echo "Start deployment Step <2/3>----------------------"
  echo "Start to deploy mysql cluster of services."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part1/ts-mysql-rdb.yaml
  echo "Waiting for mysql cluster of train-ticket to be ready ......"
  kubectl rollout status statefulset/ts-mysql-rdb
  echo "End deployment Step <1/3>----------------------"
}

function deploy_tt_mysql_each_service {
  echo "Start deployment Step <2/3>----------------------"
  echo "Start to deploy mysql cluster of services."
  #gen_mysql_and_secret_for_services
  #kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part1/ts-mysql.yaml
  deploy_mysql_and_secret_for_services
  echo "Waiting for mysql clusters of train-ticket to be ready ......"
  for s in $svc_list
  do
    kubectl rollout status statefulset/ts-$s-services
  done
  echo "End deployment Step <1/3>----------------------"
}


#!/bin/bash

TT_ROOT=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source "$TT_ROOT/gen_mysql.sh"

tsUser="ts"
tsPassword="Ts_123456"
tsDB="ts"

function deploy_tt_mysql_all_in_one {
  namespace=$1
  tsMysqlName="ts"
  tsHost="$tsMysqlName-mysql"
  echo "Start deployment Step <2/3>----------------------"
  echo "Start to deploy mysql cluster of services."
  #kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part1/ts-mysql-rdb.yaml -n $namespace
  helm install $tsMysqlName --set mysql.mysqlUser=$tsUser --set mysql.mysqlPassword=$tsPassword --set mysql.mysqlDatabase=$tsDB deployment/kubernetes-manifests/quickstart-k8s/charts/mysql -n $namespace
  echo "Waiting for mysql cluster of train-ticket to be ready ......"
  kubectl rollout status statefulset/ts-mysql -n $namespace
  gen_secret_for_tt $tsHost $tsUser $tsPassword $tsDB
  echo "End deployment Step <2/3>----------------------"
}

function deploy_tt_mysql_each_service {
  echo "Start deployment Step <2/3>----------------------"
  echo "Start to deploy mysql cluster of services."
  namespace=$1
  for s in $svc_list
  do
    mysqlName="ts-$s"
    helm install $mysqlName --set mysql.mysqlUser=$tsUser --set mysql.mysqlPassword=$tsPassword --set mysql.mysqlDatabase=$tsDB deployment/kubernetes-manifests/quickstart-k8s/charts/mysql -n $namespace
  done

  echo "Waiting for mysql clusters of train-ticket to be ready ......"
  for s in $svc_list
  do
    kubectl rollout status statefulset/ts-$s-services -n $namespace
  done

  gen_secret_for_services $tsUser $tsPassword $tsDB
  echo "End deployment Step <2/3>----------------------"
}


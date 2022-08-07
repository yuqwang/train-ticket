#!/bin/bash
svc_list="assurance auth config consign-price consign contacts delivery food-delivery inside-payment notification order-other order payment price route security station-food station ticket-office train-food train travel travel2 user voucher wait-order"

secret_yaml="deployment/kubernetes-manifests/quickstart-k8s/yamls/secret.yaml"

function gen_secret_for_tt {
  s="$1"
  name="ts-$s-mysql"
  hostVal="$2"
  userVal="$3"
  passVal="$4"
  dbVal="$5"

  prefix=`echo "${s}-mysql-" | tr '-' '_' | tr a-z A-Z`
  host=$prefix"HOST"
  port=$prefix"PORT"
  database=$prefix"DATABASE"
  user=$prefix"USER"
  pwd=$prefix"PASSWORD"

  cat>>$secret_yaml<<EOF
apiVersion: v1
kind: Secret
metadata:
  name: $name
type: Opaque
stringData:
  $host: "$hostVal"
  $port: "3306"
  $database: "$dbVal"
  $user: "$userVal"
  $pwd: "$passVal"
---
EOF
}

function gen_secret_for_services {
  mysqlUser="$1"
  mysqlPassword="$2"
  mysqlDatabase="$3"
  mysqlHost=""
  useOneHost=0

  if [ $# == 4 ]; then
    mysqlHost="$4"
    useOneHost=1
  fi
  rm $secret_yaml
  touch $secret_yaml
  for s in $svc_list
  do
    if [ useOneHost == 0 ]; then
      mysqlHost="ts-$s-mysql-leader"
    fi
    gen_secret_for_tt $s $mysqlHost $mysqlUser $mysqlPassword $mysqlDatabase
  done
}

#gen_secret_for_services ts ts ts

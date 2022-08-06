#!/bin/bash
svc_list="assurance auth config consign-price consign contacts delivery food-delivery inside-payment notification order-other order payment price route security station-food station ticket-office train-food train travel travel2 user voucher wait-order"

function gen_secret_for_nacos {
  name="$1"
  hostVal="$2"
  userVal="$3"
  passVal="$4"
  dbVal="$5"
  file="deployment/kubernetes-manifests/quickstart-k8s/part0/nacos-secret.yaml"
  cat>>$file<<EOF
apiVersion: v1
kind: Secret
metadata:
  name: $name
type: Opaque
stringData:
  MYSQL_SERVICE_HOST: "$name-leader"
  MYSQL_SERVICE_PORT: "3306"
  MYSQL_SERVICE_DB_NAME: "$dbVal"
  MYSQL_SERVICE_USER: "$userVal"
  MYSQL_SERVICE_PASSWORD: "$passVal"
---
EOF
}

function gen_secret_for_tt {
  s="$1"
  hostVal="$2"
  userVal="$3"
  passVal="$4"
  dbVal="$5"
  file="deployment/kubernetes-manifests/quickstart-k8s/part2/secret.yaml"
  prefix=`echo "${s}-mysql-" | tr '-' '_' | tr a-z A-Z`
  echo $prefix
  host=$prefix"HOST"
  echo $host
  port=$prefix"PORT"
  echo $port
  database=$prefix"DATABASE"
  echo $database
  user=$prefix"USER"
  echo $user
  pwd=$prefix"PASSWORD"
  echo $pwd

  cat>>$file<<EOF
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

function gen_mysql_and_secret_for_services {
  mysqlYaml="deployment/kubernetes-manifests/quickstart-k8s/part1/ts-mysql.yaml"
  template="deployment/kubernetes-manifests/quickstart-k8s/part1/ts-mysql-rdb.yaml"
  mv $mysqlYaml $mysqlYaml.bk
  touch $mysqlYaml

  file="deployment/kubernetes-manifests/quickstart-k8s/part2/secret.yaml"
  mv $file $file-bk
  touch $file

  for s in $svc_list
  do
    echo $s
    mysqlName="ts-$s-mysql"
    sed "s/ts-mysql-rdb/$mysqlName/g" $template >> $mysqlYaml

    # generate responding secret
    gen_secret_for_tt $s $mysqlName-leader "ts" "Ts_123456" "ts"
  done
}

function gen_secret_for_services {
  for s in $svc_list
    do
        mysqlHost="ts-$s-mysql-leader"
        mysqlUser="$1"
        mysqlPassword="$2"
        mysqlDatabase="$3"
        gen_secret_for_tt $s $mysqlHost $mysqlUser $mysqlPassword $mysqlDatabase
    done
}
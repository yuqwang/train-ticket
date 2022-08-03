#!/bin/bash
echo "Start deploy mysql cluster for nacos."
kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part0
echo "Waiting for mysql cluster of nacos to be ready ......"
kubectl rollout status statefulset/nacos-rdb
echo "Start deploy nacos, rabbitmq, mysql cluster for train-ticket services."
kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part1
echo "Waiting for nacos to be ready ......"
kubectl rollout status statefulset/nacos
echo "Waiting for mysql of services to be ready ......"
kubectl rollout status statefulset/ts-mysql-rdb
echo "Start deploy train-ticket services"
kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part2


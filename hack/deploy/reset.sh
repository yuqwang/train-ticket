#!/bin/bash
kubectl delete -f deployment/kubernetes-manifests/quickstart-k8s/part2
kubectl delete -f deployment/kubernetes-manifests/quickstart-k8s/part1
kubectl delete -f deployment/kubernetes-manifests/quickstart-k8s/part0

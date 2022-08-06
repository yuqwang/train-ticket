#！/bin/bash

function deploy_tt_cm_se {
  namespace=$1
  echo "Start deployment Step <3/3>----------------------"
  echo "Start to deploy configMaps of train-ticket services."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part2/cm.yaml -n $namespace
  echo "Start to deploy secrets of train-ticket services."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part2/secrets.yaml -n $namespace
  echo "Start to deploy services of train-ticket services."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part2/svc.yaml -n $namespace
  echo "End deployment Step <3/3>----------------------"
}

function deploy_tt_dp {
  namespace=$1
  echo "Start to deploy train-ticket deployments."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part2/deploy.yaml -n $namespace
  echo "End deployment Step <3/3>----------------------"
}

function deploy_tt_dp_sw {
  namespace=$1
  echo "Start to deploy train-ticket deployments with skywalking agent."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part2/sw_deploy.yaml -n $namespace
  echo "End deployment Step <3/3>----------------------"
}
#！/bin/bash

function deploy_tt_cm_se {
  echo "Start deployment Step <3/3>----------------------"
  echo "Start to deploy configMaps of train-ticket services."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part2/cm.yaml
  echo "Start to deploy secrets of train-ticket services."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part2/secrets.yaml
  echo "Start to deploy services of train-ticket services."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part2/svc.yaml
  echo "End deployment Step <3/3>----------------------"
}

function deploy_tt_dp {
  echo "Start to deploy train-ticket deployments."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part2/deploy.yaml
  echo "End deployment Step <3/3>----------------------"
}

function deploy_tt_dp_sw {
  echo "Start to deploy train-ticket deployments with skywalking agent."
  kubectl apply -f deployment/kubernetes-manifests/quickstart-k8s/part2/sw_deploy.yaml
  echo "End deployment Step <3/3>----------------------"
}
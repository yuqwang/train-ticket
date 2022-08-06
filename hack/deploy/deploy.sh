#!/bin/bash

TT_ROOT=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

source "$TT_ROOT/deploy-part0.sh"
source "$TT_ROOT/deploy-part1.sh"
source "$TT_ROOT/deploy-part2.sh"

namespace="$1"
args="$2"
argNone=0
argDB=0
argMonitoring=0
argTracing=0
argAll=0


function quick_start {
  deploy_part0  $namespace
  deploy_tt_mysql_all_in_one  $namespace
  deploy_tt_cm_se  $namespace
  deploy_tt_dp  $namespace
}

function deploy_all {
  deploy_monitoring
  deploy_tracing  $namespace
  deploy_part0  $namespace
  deploy_tt_mysql_each_service  $namespace
  deploy_tt_cm_se  $namespace
  deploy_tt_dp_sw  $namespace
}


function deploy {
    if [ $argNone == 1 ]; then
      quick_start
      exit $?
    fi

    if [ $argAll == 1 ]; then
      deploy_all
      exit $?
    fi

    deploy_part0
    if [ $argDB == 1 ]; then
      deploy_tt_mysql_each_service  $namespace
    fi
    deploy_tt_cm_se
    if [ $argTracing == 1 ]; then
      deploy_tracing  $namespace
      deploy_tt_dp_sw  $namespace
    fi
    if [ $argMonitoring == 1 ]; then
      deploy_monitoring
    fi
}

#deploy
function parse_args {
    for arg in $args
    do
      echo $arg
      case $arg in
      "")
        argNone=1
        ;;
      "--all")
        argAll=1
        ;;
      "--independent-db")
        argDB=1
        ;;
      "--with-monitoring")
        argMonitoring=1
        ;;
      "--with-tracing")
        argTracing=1
        ;;
      esac
    done
}

parse_args
deploy
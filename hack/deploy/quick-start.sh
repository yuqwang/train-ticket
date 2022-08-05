#!/bin/bash

TT_ROOT=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

source "$TT_ROOT/deploy-part0.sh"
source "$TT_ROOT/deploy-part1.sh"
source "$TT_ROOT/deploy-part2.sh"

function quick_start {
  deploy_part0
  deploy_tt_mysql_all_in_one
  deploy_tt_cm_se
  deploy_tt_dp
}

function deploy {
    if [ $# == 0 ]; then
      quick_start
    fi
}

deploy

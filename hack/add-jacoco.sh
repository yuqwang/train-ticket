#!/usr/bin/env bash
set -eux

echo
echo "add jacoco.jar"
echo
for dir in ts-*; do
    if [[ -d $dir ]]; then
#      echo "add-jacoco ${dir}"
 #     cp jacocoagent.jar "${dir}"/
        if [[ -n $(ls "$dir" | grep -i Dockerfile) ]]; then
#            echo "add-jacoco ${dir}"
            cp jacocoagent.jar "${dir}"/
        fi
    fi
done

#!/bin/bash

i="0"
DATE=`date '+%Y-%m-%d %H:%M:%S'`

while true
do
  docker service scale client=$i
  i=$[$i+1]
  sleep 10
  echo "Instances: $i, time: $DATE \n"
done
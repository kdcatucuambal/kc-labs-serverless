#!/bin/sh

start_time=$(date +%s)
echo "STARTING BUILD..."
. ./scripts/build_project.sh
echo "BUILD FINISHED"

echo "---------------------"

echo "STARTING RESOLVE YAML..."
. ./scripts/resolve_yaml.sh
echo "RESOLVE YAML FINISHED"

echo "STARTING DEPLOY..."
. ./scripts/deploy_stack.sh
echo "DEPLOY FINISHED"
end_time=$(date +%s)
total_time=$((end_time - start_time))
logo=$(cat << "EOF"
 /\     /\      BY KEVIN CATUCUAMBA           
{  `---'  }               
{  O   O  }
~~>  V  <~~
 \  \|/  /
  `-----'____
  /     \    \_
 {       }\  )_\_   _
 |  \_/  |/ /  \_\_/ )
  \__/  /(_/     \__/
    (__/
EOF
)
echo "--------------------------------"
echo "$logo"
echo " K C L A B S - 2 0 2 3"
echo "--------------------------------"

echo "Total time: $total_time seconds"

hours=$(( total_time / 3600 ))
seconds=$(( total_time % 3600 ))
minutes=$(( total_time / 60 ))
seconds=$(( total_time % 60 ))

formatted_time=$(printf "%02d Hours : %02d Mins : %02d Secs." $hours $minutes $seconds)

echo "Finish execution: " $formatted_time
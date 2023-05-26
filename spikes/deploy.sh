#!/bin/bash

# set default values for parameters
PARAMETER_P=true
PARAMETER_D=true

# read command line options
while getopts "PD" opt; do
  case $opt in
    P)
      PARAMETER_P=false
      ;;
    D)
      PARAMETER_D=false
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      exit 1
      ;;
    :)
      echo "Option -$OPTARG requires an argument." >&2
      exit 1
      ;;
  esac
done

cd ..

echo "Build Package ?: $PARAMETER_P"
echo "Build Dependencies ?: $PARAMETER_D"

# 1. Compile and package the application (package)
if [ "$PARAMETER_P" = true ] ; then
  echo "[EXEC] mvn clean package"
  cd kc-labs-app
  mvn clean package
  echo "---- Application packaged successfully ----"
fi

# 2. Build dependencies
if [ "$PARAMETER_D" = true ] ; then
  echo "[EXEC] mvn dependency:copy-dependencies -DoutputDirectory=dependencies/java/lib -Dmdep.prependGroupId=true -DexcludeScope=provided"
  rm -rf dependencies/
  mvn dependency:copy-dependencies -DoutputDirectory=dependencies/java/lib -Dmdep.prependGroupId=true -DexcludeScope=provided
  echo "---- Dependencies built successfully ----"
fi

ls -la

cd ..
sam --version

# 3. Sam package
packageVars=$(python3 scripts/parameters.py 'package')
echo "[EXEC] sam package --template-file template.yaml ${packageVars} --output-template-file packaged.yaml"
sam package --template-file template.yaml ${packageVars} --output-template-file packaged.yaml

# 4. Sam deploy
deployVars=$(python3 scripts/parameters.py 'deploy')
echo "[EXEC] sam deploy --template-file packaged.yaml ${deployVars}"
sam deploy --template-file packaged.yaml ${deployVars}

ls -la

echo "---- Deployment completed successfully ----"
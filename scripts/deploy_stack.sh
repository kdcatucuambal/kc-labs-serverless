#!/bin/sh
sam --version
aws --version

packageVars=$(python3 scripts/python/parameters.py 'package')
cd .stack/
#Sam package
echo "[EXEC] sam package --template-file template.yaml ${packageVars} --output-template-file packaged.yaml"
sam package --template-file ../template.yaml ${packageVars} --output-template-file packaged.yaml

cd ../
deployVars=$(python3 scripts/python/parameters.py 'deploy')
cd .stack/
#Sam deploy
echo "[EXEC] sam deploy --template-file packaged.yaml ${deployVars}"
sam deploy --template-file packaged.yaml ${deployVars}

echo "Deploy finished successfully!"

echo "Checking if is necessary to create the public DNS record..."

cd ../

. ./scripts/shell/publicApiDns.sh

echo "Finish publicApiDns.sh successfully!"

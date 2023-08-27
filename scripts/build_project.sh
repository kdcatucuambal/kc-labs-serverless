#!/bin/sh

CURRENT_BRANCH=$(git branch --show-current)
echo "Current branch: $CURRENT_BRANCH"

#check if config.json exists
if [ ! -f config/$CURRENT_BRANCH.json ]; then
    echo "ERROR: config.json not found for branch $CURRENT_BRANCH!"
    exit 1
fi

#check if .stack folder exists, if not, create it
echo "Setting workspace..."
if [ ! -d .stack ]; then
    mkdir .stack
else
    #delete all files in .stack folder except swagger-codegen-cli-3.0.5.jar
    echo "Cleaning workspace..."
    find .stack/ -type f ! -name 'swagger-codegen-cli-3.0.5.jar' -delete
    find .stack/ -type d -empty -delete
fi


cp config/$CURRENT_BRANCH.json .stack/config.json

echo "Inject config.json..."

INIT_VARIABLES=$(python3 scripts/python/inject_values.py)
echo $INIT_VARIABLES

cd .stack/

echo "Init variables..."

ENVIRONMENT_ID=$(jq -r '.parameters."EnvironmentId"' config.json)
PATH_SERVICE=$(jq -r '.pipeline.project."path-service"' config.json)

echo "EnvironmentId: $ENVIRONMENT_ID"
echo "Path service: $PATH_SERVICE"

cd ../

#Build services and dependencies
echo "Build project and dependencies..."
mvn --version

cd $PATH_SERVICE

rm -rf target
rm -rf dependencies
ls -la
echo "[EXEC] mvn clean package"
mvn clean package > /dev/null 2>&1
echo "[EXEC] mvn dependency:copy-dependencies -DoutputDirectory=dependencies/java/lib -Dmdep.prependGroupId=true -DexcludeScope=provided"
mvn dependency:copy-dependencies -DoutputDirectory=dependencies/java/lib -Dmdep.prependGroupId=true -DexcludeScope=provided
ls -la

cp ../README.md target/README.md
cd target
zip empty_function_code.zip README.md
rm README.md
cd ../../

echo "Finish build.sh successfully!"
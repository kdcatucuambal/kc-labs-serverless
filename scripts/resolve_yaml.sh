#!/bin/bash
echo "=========== Getting YAML files ============="
set -e
set -u

pwd
ls -la

mkdir -p .stack/specs
cp -r specs/* .stack/specs

cd .stack/

#check if swagger-codegen-cli-3.0.5.jar exists, if not, download it
if [ ! -f swagger-codegen-cli-3.0.5.jar ]; then
    echo "swagger-codegen-cli-3.0.5.jar not found, downloading..."
    wget https://repo1.maven.org/maven2/io/swagger/codegen/v3/swagger-codegen-cli/3.0.5/swagger-codegen-cli-3.0.5.jar
fi


echo $(date +"%Y-%m-%d %H:%M:%S,%3N")
echo "[INI] - Reading specs directory"
echo $(date +"%Y-%m-%d %H:%M:%S,%3N")
find specs/ -type f -name "*API.yaml" -exec sh -c '
for file do
   pwd
   dir=${file%/*}
   file_name_ext="${file##*/}"
   file_name="${file_name_ext%.*}"
   api_title_var=$(cat $file | grep -Po "(?<={EnvironmentId} - )(.*)(?=)")
   echo "******** api_title_var"
   echo $(date +"%Y-%m-%d %H:%M:%S,%3N")
   echo api_title_var
   echo "********"
   echo "$dir"
   echo "$file_name"
   echo "$file_name_ext"
   cp specs/domains/* $dir
   rm -rf $file.tmp > /dev/null
   rm -rf $dir/$file_name-resolved.yaml > /dev/null
   rm -rf $dir/$file_name-resolved-min.yaml > /dev/null
   echo "[INI] - Checking spec: $file_name "
   echo $(date +"%Y-%m-%d %H:%M:%S,%3N")
   sed '\''s/  title://g'\'' $file > $file.tmp
   sed '\''s/    Fn::Sub: ${EnvironmentId} - /  title: /g'\'' $file.tmp > $file.tmp1
   sed '\''s/#- url: /- url: /g'\'' $file.tmp1 > $file.tmp
   sed '\''s/#  description: URL/  description: URL/g'\'' $file.tmp > $file.tmp1
   echo "******** Borrando $file.tmp"
   echo $(date +"%Y-%m-%d %H:%M:%S,%3N")
   rm -rf $file.tmp > /dev/null
   echo "******** Validando $file.tmp1"
   echo $(date +"%Y-%m-%d %H:%M:%S,%3N")
   speccy lint $file.tmp1
   echo "[INI] - Generar YAML resuelto"
   echo $(date +"%Y-%m-%d %H:%M:%S,%3N")
   mkdir -p $dir/RESOLVE
   java -jar ./swagger-codegen-cli-3.0.5.jar generate -i $file -l openapi-yaml -o $dir/RESOLVE
   mv $dir/RESOLVE/openapi.yaml $dir/$file_name-resolved.yaml
   sed '\''s/info:/info:\n  title:\n    Fn::Sub: ${EnvironmentId} - api_title/g'\'' $dir/$file_name-resolved.yaml > $dir/$file_name-resolved.yaml1
   sed -r "s/api_title/$api_title_var/g" $dir/$file_name-resolved.yaml1 > $dir/$file_name-resolved.yaml2
   mv $dir/$file_name-resolved.yaml2 $dir/$file_name-resolved.yaml
   rm -rf $dir/$file_name-resolved.yaml1 > /dev/null
   rm -rf $dir/$file_name-resolved.yaml2 > /dev/null
   rm -rf $dir/RESOLVE
   rm -rf $file.tmp1 > /dev/null
   echo "********"
   echo $(date +"%Y-%m-%d %H:%M:%S,%3N")
done' sh {} +
echo "[END] - Reading specs directory"
cd ../
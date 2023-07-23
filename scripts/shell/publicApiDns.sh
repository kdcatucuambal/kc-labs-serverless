#!/bin/bash
#Register public record set in Route 53 in shared resources account for the api
pwd
ls -la

cd .stack/

STACK_NAME=$(jq -r '.pipeline.deploy."stack-name"' config.json)
CUSTOM_DOMAIN_NAME=$(jq -r '.pipeline.deploy.parameters[] | select(.parameterKey=="CustomDomainName").parameterValue' "config.json")
PUBLIC_CERTIFICATE_ARN=$(jq -r '.pipeline.deploy.parameters[] | select(.parameterKey=="PublicCertificateArn").parameterValue' "config.json")
ARN_API_DOMAIN_NAME=$(aws cloudformation describe-stacks --stack-name $STACK_NAME --query 'Stacks[0].Outputs[?OutputKey==`ArnApiDomainName`].OutputValue' --output text)
HOSTED_ZONE_ID=$(jq -r '.pipeline.project."hosted-zone-id"' config.json)

echo "Showing variables..."
echo "CUSTOM_DOMAIN_NAME: $CUSTOM_DOMAIN_NAME"
echo "STACK_NAME: $STACK_NAME"
echo "ARN_API_DOMAIN_NAME: $ARN_API_DOMAIN_NAME"
echo "PUBLIC_CERTIFICATE_ARN: $PUBLIC_CERTIFICATE_ARN"
echo "HOSTED_ZONE_ID: $HOSTED_ZONE_ID"

if [ -n "$CUSTOM_DOMAIN_NAME" ] && [ -n "$PUBLIC_CERTIFICATE_ARN" ] && [ -n "$ARN_API_DOMAIN_NAME" ]; then
   echo "Consulting the Record Set HZ $HOSTED_ZONE_ID Name $CUSTOM_DOMAIN_NAME"
   EXISTING_RECORD=$(aws route53 list-resource-record-sets --hosted-zone-id $HOSTED_ZONE_ID --query "ResourceRecordSets[?Name=='$CUSTOM_DOMAIN_NAME.' && Type=='CNAME'].Name" --output text)
   if [ -n "$EXISTING_RECORD" ]; then
      echo "The register or CNAME already exists in Route 53."
      echo "EXISTING_RECORD: $EXISTING_RECORD"
   else
      echo "The register or CNAME does not exist in Route 53. A new one will be created."
      #Create the CNAME record set in Route 53
      aws route53 change-resource-record-sets \
         --hosted-zone-id $HOSTED_ZONE_ID \
         --change-batch '{
            "Changes": [
            {
               "Action": "CREATE",
               "ResourceRecordSet": {
                  "Name": "'"$CUSTOM_DOMAIN_NAME"'",
                  "Type": "CNAME",
                  "TTL": 300,
                  "ResourceRecords": [
                  {
                     "Value": "'"$ARN_API_DOMAIN_NAME"'"
                  }
                  ]
               }
            }
            ]
         }'
      echo "Register or CNAME created successfully"
   fi
else                      
    echo "It is omitted to create the public DNS record since a domain is not specified"
fi
package com.kc.cloud.labs.aws.utils;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamoDb {

    public static DynamoDbClient getClient(){
        SdkHttpClient httpClient = UrlConnectionHttpClient.builder().build();
        return DynamoDbClient.builder()
                .httpClient(httpClient)
                .region(Region.US_EAST_1).build();
    }

    public static DynamoDbEnhancedClient getEnhancedClient(){
        return DynamoDbEnhancedClient.builder().dynamoDbClient(getClient()).build();
    }

}

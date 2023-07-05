package com.kc.cloud.labs.aws.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.HashMap;
import java.util.Map;

public class SecretManagerUtil {


    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, String> getValue(String secretId) {
        Map<String, String> values = null;
        Region region = Region.US_EAST_1;
        SdkHttpClient httpClient = UrlConnectionHttpClient.builder().build();
        try (SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .httpClient(httpClient)
                .build()) {
            GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                    .secretId(secretId)
                    .build();
            GetSecretValueResponse getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
            values = mapper.readValue(getSecretValueResponse.secretString(),
                    new TypeReference<HashMap<String, String>>() {});

            values.put("url", "jdbc:postgresql://" + values.get("endpoint") + ":" + values.get("port") +
                    "/" + values.get("dbname") + values.get("config"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return values;
    }

}

package com.kc.cloud.labs.aws.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.cloud.api.ApiGatewayUtil;
import com.kc.cloud.labs.aws.lambda.products.LabsProductsGETAll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class KcUtil {
    private final static Logger logger = Logger.getLogger(KcUtil.class.getName());
    private final static ApiGatewayUtil apiGatewayUtil = new ApiGatewayUtil();
    public static String getAiKeyValue(){
        logger.info("ApiGateway Client Ref: " + apiGatewayUtil);
        String apiKeyId = System.getenv("API_KEY_ID");
        logger.info("API Key Id: " + apiKeyId);
        String apiKey = apiGatewayUtil.getApiKeyWithId(apiKeyId);
        logger.info("API Key Value: " + apiKey);
        return apiKey;
    }

}

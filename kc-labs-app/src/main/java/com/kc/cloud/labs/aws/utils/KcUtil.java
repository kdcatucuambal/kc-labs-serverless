package com.kc.cloud.labs.aws.utils;


import com.kc.cloud.api.ApiGatewayUtil;



import java.util.Map;
import java.util.logging.Logger;


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

    public static Map<String, String> getCredentialsFromEnvs(){
        Map<String, String> secterMap = Map.of(
                "url", System.getenv("DATABASE_URL"),
                "username", System.getenv("DATABASE_USERNAME"),
                "password", System.getenv("DATABASE_PASSWORD")
        );
        return secterMap;
    }

}

package com.kc.cloud.labs.aws.lambda.greetings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.logging.Logger;

public class LabsGreetingsGETHello implements RequestHandler<
        APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger logger = Logger.getLogger(LabsGreetingsGETHello.class.getName());

    @Override
    public APIGatewayProxyResponseEvent handleRequest(
            APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        logger.info("LabsGreetingsGETHello.handleRequest() invoked");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        System.out.println("ApiId:" + apiGatewayProxyRequestEvent);
        HashMap<String, CustomDependency> deps = new HashMap<>();
        deps.put("rds", new CustomDependency("Hello Mysql", "jdbc:mysql://localhost:3306/"));
        deps.put("s3", new CustomDependency("Hello S3", "https://s3.amazonaws.com/"));
        deps.put("dynamodb", new CustomDependency("Hello Dynamo", "https://dynamodb.us-east-1.amazonaws.com/"));
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = mapper.writeValueAsString(deps);
            logger.info("jsonResponse: " + jsonResponse);
        } catch (JsonProcessingException e) {
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
        logger.info("jsonResponse: " + jsonResponse);

        return response
                .withStatusCode(200)
                .withBody(jsonResponse);
    }

}

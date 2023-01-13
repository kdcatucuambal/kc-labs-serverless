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
        deps.put("rds", new CustomDependency("RDS", "jdbc:mysql://localhost:3306/"));
        deps.put("s3", new CustomDependency("S3", "https://s3.amazonaws.com/"));
        deps.put("dynamodb", new CustomDependency("DynamoDB", "https://dynamodb.us-east-1.amazonaws.com/"));
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = null;
        try {
            jsonResponse = mapper.writeValueAsString(deps);
            logger.info("jsonResponse: " + jsonResponse);
        } catch (JsonProcessingException e) {
            logger.severe("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
        logger.info("jsonResponse: " + jsonResponse);


        final String pageContents;
        try {
            pageContents = this.getPageContents("https://checkip.amazonaws.com");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents);
            response.withStatusCode(500).withBody(output);
            //System.out.println("Response: " + mapper.writeValueAsString(response));

            if (response.getStatusCode() != 200) {
                try {
                    throw new IOException("Error, dependency not available");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return response;

    }

    private String getPageContents(String address) throws IOException {
        URL url = new URL(address);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}

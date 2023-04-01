package com.kc.cloud.labs.aws.lambda.balances;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.cloud.labs.aws.lambda.greetings.LabsGreetingGETName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//TODO: Add the following imports:
public class LabsBalancesGETById implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesGETById.class.getName());

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        String requestBody = new BufferedReader(new InputStreamReader(input))
                .lines().collect(Collectors.joining(System.lineSeparator()));

        logger.info("requestBody: " + requestBody);

        CustomResponse response = new CustomResponse();
        Map<String, String>  headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        response.setHeaders(headers);
        response.setStatusCode(409);

        response.setBody("{\"message\":\"Hello from LabsBalancesGETById\"}");

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(response);

        logger.info("Response: " + jsonResponse);
        output.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
    }


//    @Override
//    public CustomResponse handleRequest(APIGatewayProxyRequestEvent input, Context context) {
//
//        logger.info("Input: " + input);
//
//        //APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
//        CustomResponse response = new CustomResponse();
//        Map<String, String>  headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        headers.put("X-Custom-Header", "application/json");
//        response.setHeaders(headers);
//        response.setStatusCode(200);
//        response.setBody("{\"message\":\"Hello from LabsBalancesGETById\"}");
//
//
//        logger.info("Response: " + response);
//
//        return response;
//    }
}

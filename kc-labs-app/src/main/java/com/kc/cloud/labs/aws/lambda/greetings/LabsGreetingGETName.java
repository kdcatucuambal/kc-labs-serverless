package com.kc.cloud.labs.aws.lambda.greetings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LabsGreetingGETName implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsGreetingGETName.class.getName());
    /**
     * This method is called when the Lambda function is invoked.
     * Function to get the name of the person.
     */
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String requestBody = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        logger.info("requestBody: " + requestBody);


        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);
        String code = RandomStringUtils.random(10, true, true);
        response.setBody("{ \"name\": \"Hello Kevin, your code was created\", \"code\": \"" + code + "\" }");
        logger.info("Response: " + response);
        outputStream.write(response.toString().getBytes(StandardCharsets.UTF_8));
    }
}

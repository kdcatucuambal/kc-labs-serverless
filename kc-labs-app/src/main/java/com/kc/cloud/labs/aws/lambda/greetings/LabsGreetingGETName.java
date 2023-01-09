package com.kc.cloud.labs.aws.lambda.greetings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

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
        LambdaLogger logger = context.getLogger();
        String requestBody = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println("REQUEST: " + requestBody);
        try (inputStream; outputStream) {
            outputStream.write("{\"body\":\"Hello John\"}".getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            logger.log("Error in getting the name");
        }

    }
}

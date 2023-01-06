package com.kc.cloud.labs.aws.lambda.greetings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.*;
import java.nio.charset.Charset;
import java.util.logging.Logger;

public class LabsGreetingGETName implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsGreetingGETName.class.getName());
    /**
     * This method is called when the Lambda function is invoked.
     * Function to get the name of the person.
     * @param inputStream
     * @param outputStream
     * @param context
     * @throws IOException
     */
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        LambdaLogger logger = context.getLogger();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
                    Charset.forName("UTF-8")));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream,
                    Charset.forName("UTF-8"))));
            writer.write("{\"name\":\"John\"}");
        }catch (Exception e){
            logger.log("Error in getting the name");
        }finally {
            inputStream.close();
            outputStream.close();
        }

    }
}

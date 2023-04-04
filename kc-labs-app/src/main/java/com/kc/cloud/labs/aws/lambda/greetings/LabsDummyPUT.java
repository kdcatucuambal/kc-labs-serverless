package com.kc.cloud.labs.aws.lambda.greetings;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.cloud.labs.aws.lambda.balances.LabsBalancesGETById;
import com.kc.cloud.labs.aws.models.Record;
import com.kc.cloud.labs.aws.models.Response;

import java.io.*;
import java.util.Date;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LabsDummyPUT implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesGETById.class.getName());
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String request = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        logger.info("Info Request: " + request);

        Record record = new Record();
        record.setDate(new Date());
        record.setType("PUTTER");
        record.setDescription("This is a PUT request");

        Response<Record> response = new Response<>();

        response.setStatusCode(200);
        response.setBody(record);

        String jsonResponse = this.mapper.writeValueAsString(response);
        outputStream.write(jsonResponse.getBytes());

    }
}

package com.kc.cloud.labs.aws.lambda.balances;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Subsegment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.cloud.labs.aws.models.app.Balance;
import com.kc.cloud.labs.aws.models.app.Response;
import com.kc.cloud.labs.aws.services.BalanceService;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LabsBalancesGETAll implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesGETAll.class.getName());
    private final BalanceService balanceService = new BalanceService();

    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        logger.info("Invoking lambda: LabsBalancesGETAll");
        String request = this.getRequestInput(inputStream);
        logger.info("Request: " + request);
        List<Balance> balances = this.balanceService.getAllBalances();
        String jsonResponse = this.mapper.writeValueAsString(this.getResponse(balances));
        logger.info("Response: " + jsonResponse);
        outputStream.write(jsonResponse.getBytes());
    }

    public Response<List<Balance>> getResponse(List<Balance> balances) throws JsonProcessingException {
        Response<List<Balance>> response = new Response<>();
        response.setStatusCode(200);
        response.setBody(balances);
        return response;
    }

    public String getRequestInput(InputStream input) throws IOException {
        return  new BufferedReader(new InputStreamReader(input))
                .lines().collect(Collectors.joining("\n"));
    }

}

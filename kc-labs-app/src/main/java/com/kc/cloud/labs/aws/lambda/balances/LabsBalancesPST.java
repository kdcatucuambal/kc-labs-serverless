package com.kc.cloud.labs.aws.lambda.balances;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.cloud.labs.aws.models.app.Balance;
import com.kc.cloud.labs.aws.models.app.Response;
import com.kc.cloud.labs.aws.models.dtos.BalanceDto;
import com.kc.cloud.labs.aws.models.request.RequestObject;
import com.kc.cloud.labs.aws.models.request.ResponseObject;
import com.kc.cloud.labs.aws.services.BalanceService;
import com.kc.cloud.labs.aws.utils.KcUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LabsBalancesPST implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesPST.class.getName());
    private final BalanceService balanceService = new BalanceService();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        logger.info("Invoking lambda: LabsBalancesPST");
        String jsonRequest = KcUtil.convertInputStreamToString(inputStream);
        logger.info("Request received: " + jsonRequest);
        RequestObject<BalanceDto> requestObject = KcUtil.deserializeObject(jsonRequest, new TypeReference<RequestObject<BalanceDto>>() {});
        Balance balanceCreated = balanceService.saveBalance(requestObject.getBody());
        String jsonResponse = KcUtil.serializeObject(this.getResponse(balanceCreated));
        logger.info("Response: " + jsonResponse);
        outputStream.write(jsonResponse.getBytes());
    }

    public Response<Balance> getResponse(Balance balance) {
        Response<Balance> response = new Response<>();
        response.setStatusCode(201);
        response.setBody(balance);
        return response;
    }

    public String getRequestInput(InputStream input) throws IOException {
        return  new BufferedReader(new InputStreamReader(input))
                .lines().collect(Collectors.joining(System.lineSeparator()));
    }

}

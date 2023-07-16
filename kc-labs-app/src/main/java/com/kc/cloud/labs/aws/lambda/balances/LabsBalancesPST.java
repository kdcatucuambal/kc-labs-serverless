package com.kc.cloud.labs.aws.lambda.balances;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kc.cloud.labs.aws.models.app.Balance;
import com.kc.cloud.labs.aws.models.app.Response;
import com.kc.cloud.labs.aws.models.request.RequestObject;
import com.kc.cloud.labs.aws.utils.BalanceDao;
import com.kc.cloud.util.ConvertDataUtil;

import java.io.*;
import java.util.logging.Logger;

public class LabsBalancesPST implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesPST.class.getName());
    private final BalanceDao balanceV2Dao = new BalanceDao();

    public LabsBalancesPST() {
        logger.info("Initializing lambda: LabsBalancesPST");
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        logger.info("Invoking lambda: LabsBalancesPST");
        String jsonRequest = ConvertDataUtil.convertInputStreamToString(inputStream);
        logger.info("Request: " + jsonRequest);
        RequestObject<Balance> requestObject = ConvertDataUtil.deserializeObject(jsonRequest, new TypeReference<RequestObject<Balance>>() {});
        boolean balanceCreated = balanceV2Dao.save(requestObject.getBody());
        logger.info("Balance created: " + balanceCreated);
        String jsonResponse = ConvertDataUtil.serializeObject(this.getResponse(requestObject.getBody()));
        logger.info("Response: " + jsonResponse);
        outputStream.write(jsonResponse.getBytes());
    }

    public Response<Balance> getResponse(Balance balance) {
        Response<Balance> response = new Response<>();
        response.setStatusCode(201);
        response.setBody(balance);
        return response;
    }


}

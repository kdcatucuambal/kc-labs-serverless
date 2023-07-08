package com.kc.cloud.labs.aws.lambda.balances;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.cloud.labs.aws.models.app.BalanceV2;
import com.kc.cloud.labs.aws.models.app.Response;
import com.kc.cloud.labs.aws.utils.BalanceV2Dao;
import com.kc.cloud.util.ConvertDataUtil;


import java.io.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LabsBalancesGETAll implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesGETAll.class.getName());
    private final BalanceV2Dao balanceV2Dao = new BalanceV2Dao();

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        logger.info("Invoking lambda: LabsBalancesGETAll");
        String request = ConvertDataUtil.convertInputStreamToString(inputStream);
        logger.info("Request: " + request);
        List<BalanceV2> balances = this.balanceV2Dao.findAll();
        String jsonResponse = ConvertDataUtil.serializeObject(this.getResponse(balances));
        logger.info("Response: " + jsonResponse);
        outputStream.write(jsonResponse.getBytes());
    }

    public Response<List<BalanceV2>> getResponse(List<BalanceV2> balances) throws JsonProcessingException {
        Response<List<BalanceV2>> response = new Response<>();
        response.setStatusCode(200);
        response.setBody(balances);
        return response;
    }


}

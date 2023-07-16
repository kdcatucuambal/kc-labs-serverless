package com.kc.cloud.labs.aws.lambda.balances;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kc.cloud.labs.aws.models.app.Balance;
import com.kc.cloud.labs.aws.models.app.Response;
import com.kc.cloud.labs.aws.utils.BalanceDao;
import com.kc.cloud.util.ConvertDataUtil;


import java.io.*;
import java.util.List;
import java.util.logging.Logger;

public class LabsBalancesGETAll implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesGETAll.class.getName());
    private final BalanceDao balanceV2Dao = new BalanceDao();

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        logger.info("Invoking lambda: LabsBalancesGETAll");
        String request = ConvertDataUtil.convertInputStreamToString(inputStream);
        logger.info("Request: " + request);
        List<Balance> balances = this.balanceV2Dao.findAll();
        String jsonResponse = ConvertDataUtil.serializeObject(this.getResponse(balances));
        logger.info("Response: " + jsonResponse);
        outputStream.write(jsonResponse.getBytes());
    }

    public Response<List<Balance>> getResponse(List<Balance> balances) throws JsonProcessingException {
        Response<List<Balance>> response = new Response<>();
        response.setStatusCode(200);
        response.setBody(balances);
        return response;
    }


}

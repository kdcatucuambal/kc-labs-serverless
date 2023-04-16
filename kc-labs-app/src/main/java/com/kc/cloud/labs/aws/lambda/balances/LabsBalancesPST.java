package com.kc.cloud.labs.aws.lambda.balances;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kc.cloud.labs.aws.models.app.Balance;
import com.kc.cloud.labs.aws.models.dtos.BalanceDto;
import com.kc.cloud.labs.aws.models.request.RequestObject;
import com.kc.cloud.labs.aws.models.request.ResponseObject;
import com.kc.cloud.labs.aws.services.BalanceService;
import com.kc.cloud.labs.aws.utils.KcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class LabsBalancesPST implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesPST.class.getName());
    private final BalanceService balanceService = new BalanceService();



    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String jsonRequest = KcUtil.convertInputStreamToString(inputStream);
        logger.info("Info Request: " + jsonRequest);
        RequestObject<BalanceDto> requestObject = KcUtil.deserializeObject(jsonRequest, new TypeReference<RequestObject<BalanceDto>>() {});

        Balance balanceCreated = balanceService.saveBalance(requestObject.getBody());

        ResponseObject<Balance> responseObject = new ResponseObject<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("x-api-auth", "kcatucuamba");

        responseObject.setBody(balanceCreated);
        responseObject.setHeaders(headers);

        String jsonResponse = KcUtil.serializeObject(responseObject);

        outputStream.write(jsonResponse.getBytes());
    }



}

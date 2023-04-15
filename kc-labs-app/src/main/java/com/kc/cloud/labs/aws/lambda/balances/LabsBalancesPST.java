package com.kc.cloud.labs.aws.lambda.balances;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.kc.cloud.labs.aws.services.BalanceService;
import com.kc.cloud.labs.aws.utils.KcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

public class LabsBalancesPST implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesPST.class.getName());
    private final BalanceService balanceService = new BalanceService();



    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String jsonRequest = KcUtil.convertInputStreamToString(inputStream);
        logger.info("Info Request: " + jsonRequest);
        outputStream.write("Balance created".getBytes());
    }



}

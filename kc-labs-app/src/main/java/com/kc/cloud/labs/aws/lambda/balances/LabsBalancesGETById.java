package com.kc.cloud.labs.aws.lambda.balances;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.cloud.labs.aws.models.app.BalanceV2;
import com.kc.cloud.labs.aws.models.app.Response;
import com.kc.cloud.labs.aws.utils.BalanceV2Dao;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class LabsBalancesGETById implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesGETById.class.getName());
    private final BalanceV2Dao balanceV2Dao = new BalanceV2Dao();
    private final ObjectMapper mapper = new ObjectMapper();
    public LabsBalancesGETById() {
        logger.info("LabsBalancesGETById constructor");

    }

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        String request = this.getRequestInput(input);
        logger.info("Request: " + request);
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {};
        Map<String, Object> requestMap = mapper.readValue(request, typeRef);
        logger.info("Info requestMap: " + requestMap.toString());
        Map<String, Object> getbody = (Map<String, Object>)requestMap.get("getbody");
        Map<String, String> params = (Map<String, String>)getbody.get("params");
        String balanceId = params.get("id");
        logger.info("Info balanceId: " + balanceId);
        BalanceV2 balanceFound = this.balanceV2Dao.findById(balanceId);
        String jsonResponse = this.mapper.writeValueAsString(this.getResponse(balanceFound));
        logger.info("Response: " + jsonResponse);
        output.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
    }

    public Response<BalanceV2> getResponse(BalanceV2 balance) {
        Response<BalanceV2> response = new Response<>();
        response.setStatusCode(200);
        response.setBody(balance);
        return response;
    }

    public String getRequestInput(InputStream input) throws IOException {
        return  new BufferedReader(new InputStreamReader(input))
                .lines().collect(Collectors.joining(System.lineSeparator()));
    }

}

package com.kc.cloud.labs.aws.lambda.balances;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Subsegment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.cloud.labs.aws.models.app.Balance;
import com.kc.cloud.labs.aws.models.app.CustomResponse;
import com.kc.cloud.labs.aws.models.app.Response;
import com.kc.cloud.labs.aws.models.app.UserSample;
import com.kc.cloud.labs.aws.services.BalanceService;
import com.kc.cloud.labs.aws.utils.SecretManagerUtil;
import com.kc.cloud.labs.aws.utils.SqlSampleClient;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class LabsBalancesGETById implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesGETById.class.getName());
    private final BalanceService balanceService;
    private final ObjectMapper mapper = new ObjectMapper();
    public LabsBalancesGETById() {
        logger.info("LabsBalancesGETById constructor");
        this.balanceService = new BalanceService();
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
        Balance balanceFound = this.balanceService.getBalanceByCode(balanceId);

        logger.info("Simpe sql client test: ");
        long startTime = System.currentTimeMillis();
        Map<String, String> secretMap = SecretManagerUtil.getValue("dev1/credentials/database");
        List<UserSample> users = SqlSampleClient.getAll(secretMap.get("url"), secretMap.get("username"), secretMap.get("password"));
        users.forEach(System.out::println);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + executionTime + " milisegundos");

        String jsonResponse = this.mapper.writeValueAsString(this.getResponse(balanceFound));
        logger.info("Response: " + jsonResponse);
        output.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
    }

    public Response<Balance> getResponse(Balance balance) {
        Response<Balance> response = new Response<>();
        response.setStatusCode(200);
        response.setBody(balance);
        return response;
    }

    public String getRequestInput(InputStream input) throws IOException {
        return  new BufferedReader(new InputStreamReader(input))
                .lines().collect(Collectors.joining(System.lineSeparator()));
    }

}

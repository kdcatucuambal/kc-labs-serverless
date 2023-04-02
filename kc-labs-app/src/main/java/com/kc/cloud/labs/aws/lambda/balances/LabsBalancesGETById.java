package com.kc.cloud.labs.aws.lambda.balances;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Segment;
import com.amazonaws.xray.entities.Subsegment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.cloud.labs.aws.models.Balance;
import com.kc.cloud.labs.aws.services.BalanceService;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class LabsBalancesGETById implements RequestStreamHandler {

    private static final Logger logger = Logger.getLogger(LabsBalancesGETById.class.getName());
    private BalanceService balanceService;
    public LabsBalancesGETById() {
        logger.info("LabsBalancesGETById constructor");
        this.balanceService = new BalanceService();
    }

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        String request = new BufferedReader(new InputStreamReader(input))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        logger.info("Info Request: " + request);
        //Get a Mappping of the request
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {};
        Map<String, Object> requestMap = mapper.readValue(request, typeRef);

        Object pathPatametersObject = requestMap.get("pathParameters");

        Subsegment subsegment = AWSXRay.beginSubsegment("Get Balance By Id");

        Map<String, String> pathPatameters = (Map<String, String>) pathPatametersObject;

        String balanceId = pathPatameters.get("id");

        logger.info("Info balanceId: " + balanceId);

        logger.info("Info pathPatameters: " + pathPatameters.toString());
        logger.info("Type pathPatameters: " + pathPatameters.getClass().getName());

        Balance balanceFound = this.balanceService.getBalanceByCode(balanceId);
        String balanceFoundJson = mapper.writeValueAsString(balanceFound);

        CustomResponse response = new CustomResponse();


        AWSXRay.endSubsegment();



        Map<String, String>  headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        headers.put("kec-header", "kec-value");
        response.setHeaders(headers);
        response.setStatusCode(200);
        response.setBody(balanceFoundJson);

        String responseJson = mapper.writeValueAsString(response);
        output.write(responseJson.getBytes(StandardCharsets.UTF_8));
    }


//    @Override
//    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
//        String requestBody = new BufferedReader(new InputStreamReader(input))
//                .lines().collect(Collectors.joining(System.lineSeparator()));
//
//        logger.info("requestBody: " + requestBody);
//
//        CustomResponse response = new CustomResponse();
//        Map<String, String>  headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        headers.put("X-Custom-Header", "application/json");
//        response.setHeaders(headers);
//        response.setStatusCode(200);
//
//        response.setBody("{\"message\":\"Hello from LabsBalancesGETById\"}");
//
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonResponse = mapper.writeValueAsString(response);
//
//        logger.info("Response: " + jsonResponse);
//        output.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
//    }


//    @Override
//    public CustomResponse handleRequest(APIGatewayProxyRequestEvent input, Context context) {
//
//        logger.info("Input: " + input);
//
//        //APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
//        CustomResponse response = new CustomResponse();
//        Map<String, String>  headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        headers.put("X-Custom-Header", "application/json");
//        response.setHeaders(headers);
//        response.setStatusCode(200);
//        response.setBody("{\"message\":\"Hello from LabsBalancesGETById\"}");
//
//
//        logger.info("Response: " + response);
//
//        return response;
//    }
}

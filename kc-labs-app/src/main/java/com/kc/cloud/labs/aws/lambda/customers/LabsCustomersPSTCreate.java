package com.kc.cloud.labs.aws.lambda.customers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.kc.cloud.labs.aws.lambda.customers.models.Customer;
import com.kc.cloud.labs.aws.lambda.customers.services.CustomerService;

import java.util.HashMap;
import java.util.logging.Logger;


public class LabsCustomersPSTCreate implements RequestHandler
        <APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger logger = Logger.getLogger(LabsCustomersPSTCreate.class.getName());


    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        logger.info("LabsCustomersPSTCreate.handleRequest() invoked");
        String body = input.getBody();
        logger.info("body: " + body);
        Customer customerCreated = CustomerService.createCustomer(body);
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(201)
                .withHeaders(getHeaders())
                .withBody("{\"message\": \"Customer created\", \"customerId\": \"" + customerCreated.getId() + "\"}");
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        return headers;
    }
}

package com.kc.cloud.labs.aws.lambda.customers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.kc.cloud.labs.aws.lambda.customers.models.Customer;
import com.kc.cloud.labs.aws.lambda.customers.services.CustomerService;

import java.util.HashMap;
import java.util.logging.Logger;

public class LabsCustomersGETById implements RequestHandler
        <APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger logger = Logger.getLogger(LabsCustomersGETById.class.getName());

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

        logger.info("LabsCustomersGETById.handleRequest() invoked");

        String id = input.getPathParameters().get("id");
        Customer customer = CustomerService.getCustomerById(Integer.parseInt(id));

        if (customer == null) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(404)
                    .withBody("{\"message\": \"Customer not found\"}");
        }

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withHeaders(getHeaders())
                .withBody(customer.toJSON());
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        return headers;
    }

}

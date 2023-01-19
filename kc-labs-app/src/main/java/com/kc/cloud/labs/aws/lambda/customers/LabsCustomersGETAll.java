package com.kc.cloud.labs.aws.lambda.customers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.kc.cloud.labs.aws.lambda.customers.models.Customer;
import com.kc.cloud.labs.aws.lambda.customers.services.CustomerService;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class LabsCustomersGETAll implements RequestHandler
        <APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger logger = Logger.getLogger(LabsCustomersGETAll.class.getName());

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        logger.info("LabsCustomersGETAll.handleRequest() invoked");
        List<Customer> customers = CustomerService.getAllCustomers();
        response.setHeaders(getHeaders());
        response.setBody(getBody(customers));
        response.setStatusCode(200);
        return response;
    }

    public String getBody(List<Customer> customers){
        StringBuilder customersJson = new StringBuilder("[");
        for (Customer customer : customers) {
            customersJson.append(customer.toJSON()).append(",");
        }
        customersJson.deleteCharAt(customersJson.length() - 1);
        customersJson.append("]");
        return customersJson.toString();
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        return headers;
    }
}

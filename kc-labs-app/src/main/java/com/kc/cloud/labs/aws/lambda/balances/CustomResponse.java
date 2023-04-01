package com.kc.cloud.labs.aws.lambda.balances;

import java.util.Map;

public class CustomResponse {

    private Integer statusCode;
    private String body;

    private Map<String, String> headers;

    public CustomResponse() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}

package com.kc.cloud.labs.aws.models.request;

import java.util.Map;

public class ResponseObject<T> {
    private Map<String, String> headers;
    private T body;

    public ResponseObject(Map<String, String> headers, T body) {
        this.headers = headers;
        this.body = body;
    }

    public ResponseObject() {

    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}

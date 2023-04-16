package com.kc.cloud.labs.aws.models.app;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Response<T> {
    private Integer statusCode;

    private Map<String, String> headers;

    private T body;

    public Response() {}

    public Response(Integer statusCode, Map<String, String> headers, T body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }
}

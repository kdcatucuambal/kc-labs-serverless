package com.kc.cloud.labs.aws.models.request;

import java.util.Map;

public class RequestObject<T> {

    private Map<String, String> header;

    private T body;

    private GETBody getbody;

    private Service service;


    public RequestObject() {
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public GETBody getGetbody() {
        return getbody;
    }

    public void setGetbody(GETBody getbody) {
        this.getbody = getbody;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}

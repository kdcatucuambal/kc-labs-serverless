package com.kc.cloud.labs.aws.models.request;

import java.util.Map;

public class GETBody {

    private Map<String, String> params;

    private Map<String, String> queries;

    public GETBody(){}

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, String> getQueries() {
        return queries;
    }

    public void setQueries(Map<String, String> queries) {
        this.queries = queries;
    }
}

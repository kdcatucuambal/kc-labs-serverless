package com.kc.cloud.labs.aws.lambda.greetings;

public class CustomDependency {
    public String expectedResult;
    public String resourceUrl;

    public CustomDependency(){}

    public CustomDependency(String expectedResult, String resourceUrl) {
        this.expectedResult = expectedResult;
        this.resourceUrl = resourceUrl;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
}

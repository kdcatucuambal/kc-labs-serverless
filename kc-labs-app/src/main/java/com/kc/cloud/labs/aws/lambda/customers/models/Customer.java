package com.kc.cloud.labs.aws.lambda.customers.models;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Customer {

    private Integer id;
    private String fullName;
    private Boolean isPremium;

    public Customer() {}

    public Customer(Integer id, String fullName, Boolean isPremium) {
        this.id = id;
        this.fullName = fullName;
        this.isPremium = isPremium;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", isPremium=" + isPremium +
                '}';
    }

    public String toJSON() {
        return "{" +
                "\"id\":" + id +
                ", \"fullName\":\"" + fullName + "\"" +
                ", \"isPremium\":" + isPremium +
                "}";
    }

    public void fromJSON(String json) {
        System.out.println("Customer.fromJSON() invoked");
        System.out.println("json = " + json);

    }
}

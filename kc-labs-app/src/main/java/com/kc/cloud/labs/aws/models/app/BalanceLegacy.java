package com.kc.cloud.labs.aws.models.app;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BalanceLegacy {

    public String code;

    public String username;

    public BigDecimal average;

    public BigDecimal current;

    public List<Record> records;

    public BalanceLegacy(){}

    public BalanceLegacy(String code, String username, BigDecimal average, BigDecimal current, List<Record> records){
        this.code = code;
        this.username = username;
        this.average = average;
        this.current = current;
        this.records = records;
    }

    public BalanceLegacy(String code, String username, BigDecimal average, BigDecimal current){
        this.code = code;
        this.username = username;
        this.average = average;
        this.current = current;
        this.records = new ArrayList<>();
    }


}

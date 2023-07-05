package com.kc.cloud.labs.aws.models.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceV2 {

    public Integer id;
    public String code;
    public String user;
    public String region;
    public BigDecimal average;
    public BigDecimal current;
    public Boolean active;

    @Override
    public String toString() {
        return "BalanceV2{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", user='" + user + '\'' +
                ", region='" + region + '\'' +
                ", average=" + average +
                ", current=" + current +
                ", active=" + active +
                '}';
    }
}

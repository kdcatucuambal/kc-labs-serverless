package com.kc.cloud.labs.aws.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {

    public String code;
    public String username;
    public BigDecimal average;
    public BigDecimal current;

    public String toString(){
        return "BalanceDto{" +
                "code='" + code + '\'' +
                ", username='" + username + '\'' +
                ", average=" + average +
                ", current=" + current +
                '}';
    }

}

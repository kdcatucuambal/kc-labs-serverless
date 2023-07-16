package com.kc.cloud.labs.aws.models.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

    private Integer id;
    private String code;
    private String user;
    private String region;
    private BigDecimal average;
    private BigDecimal current;
    private Boolean active;
    private Date cuttoffDate;
    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", user='" + user + '\'' +
                ", region='" + region + '\'' +
                ", average=" + average +
                ", current=" + current +
                ", active=" + active +
                ", cuttoffDate=" + cuttoffDate +
                '}';
    }
}

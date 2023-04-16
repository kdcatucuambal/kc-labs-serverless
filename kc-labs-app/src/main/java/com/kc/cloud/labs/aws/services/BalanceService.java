package com.kc.cloud.labs.aws.services;

import com.kc.cloud.labs.aws.models.app.Balance;
import com.kc.cloud.labs.aws.models.app.Record;
import com.kc.cloud.labs.aws.models.dtos.BalanceDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class BalanceService {
    private static final Logger logger = Logger.getLogger(BalanceService.class.getName());

    public BalanceService(){
        logger.info("BalanceService initialized!");
        logger.info(this.toString());
    }
    public Balance getBalanceByCode(String code){
        Balance balance = new Balance();
        balance.setCode(code);
        balance.setCurrent(new BigDecimal("448.26"));
        balance.setAverage(new BigDecimal("800.50"));
        balance.setUsername("Kevin Catucuamba");
        List<Record> records = new ArrayList<>();
        records.add(new Record(new Date(), "Amazon", "debit".concat(" - ").concat(code)));
        records.add(new Record(new Date(), "Google", "debit".concat(" - ").concat(code)));
        records.add(new Record(new Date(), "Facebook", "debit".concat(" - ").concat(code)));
        balance.setRecords(records);
        return balance;
    }

    public List<Balance> getAllBalances(){
        List<Balance> balances = new ArrayList<>();
        balances.add(getBalanceByCode("AB-1050"));
        balances.add(getBalanceByCode("AB-1051"));
        balances.add(getBalanceByCode("AB-1052"));
        balances.add(getBalanceByCode("AB-1053"));
        return balances;
    }

    public Balance saveBalance(BalanceDto balanceDto){
        logger.info("Saving balance DTO: " + balanceDto.toString());
        Balance balance = new Balance();
        balance.setCode(balanceDto.getCode());
        balance.setCurrent(balanceDto.getCurrent());
        balance.setAverage(balanceDto.getAverage());
        balance.setUsername(balanceDto.getUsername());
        balance.setRecords(new ArrayList<>());
        return balance;
    }

    public void deleteBalance(Balance balance){

    }

    public void updateBalance(Balance balance){

    }

}

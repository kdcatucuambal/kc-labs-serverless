package com.kc.cloud.labs.aws.utils;

import com.kc.cloud.labs.aws.models.app.Balance;

public class BalanceDao extends BaseDao<Balance> {

    public BalanceDao(){
        super("kec_balances", "bal", Balance.class);
    }

}

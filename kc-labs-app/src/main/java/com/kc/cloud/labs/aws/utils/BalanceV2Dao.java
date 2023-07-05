package com.kc.cloud.labs.aws.utils;

import com.kc.cloud.labs.aws.models.app.BalanceV2;

public class BalanceV2Dao extends BaseDao<BalanceV2> {

    public BalanceV2Dao(){
        super("kec_balances", "bal", BalanceV2.class);
    }

}

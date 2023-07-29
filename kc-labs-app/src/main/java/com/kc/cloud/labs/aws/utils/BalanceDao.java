package com.kc.cloud.labs.aws.utils;

import com.kc.cloud.dao.BaseDao;
import com.kc.cloud.labs.aws.models.app.Balance;

public class BalanceDao extends BaseDao<Balance> {

    public BalanceDao(){
        super("labs_balances", "bal", Balance.class);
    }

}

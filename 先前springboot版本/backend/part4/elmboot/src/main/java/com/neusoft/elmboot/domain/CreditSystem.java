package com.neusoft.elmboot.domain;

import com.neusoft.elmboot.domain.impl.RechargeCreditRule;
import com.neusoft.elmboot.domain.impl.SignCreditRule;
import com.neusoft.elmboot.domain.impl.TransferMoneyCreditRule;
import com.neusoft.elmboot.po.ConsumeCredit;

public interface CreditSystem {
    public int queryEarningCreditBySign(int count, SignCreditRule signCreditRule);
    public int queryEarnCreditByRecharge(int money, RechargeCreditRule rechargeCreditRule);
    public ConsumeCredit consumeCreditByPaying(Integer money, Integer creditNum, TransferMoneyCreditRule transferMoneyCreditRule);
}

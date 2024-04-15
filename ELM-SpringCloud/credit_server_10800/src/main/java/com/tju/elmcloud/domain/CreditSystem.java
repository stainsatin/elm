package com.tju.elmcloud.domain;

import com.tju.elmcloud.domain.impl.RechargeCreditRule;
import com.tju.elmcloud.domain.impl.SignCreditRule;
import com.tju.elmcloud.domain.impl.TransferMoneyCreditRule;
import com.tju.elmcloud.vo.ConsumeCredit;

public interface CreditSystem {
    public int queryEarningCreditBySign(int count, SignCreditRule signCreditRule);
    public int queryEarnCreditByRecharge(int money, RechargeCreditRule rechargeCreditRule);
    public ConsumeCredit consumeCreditByPaying(Integer money, Integer creditNum, TransferMoneyCreditRule transferMoneyCreditRule);
}

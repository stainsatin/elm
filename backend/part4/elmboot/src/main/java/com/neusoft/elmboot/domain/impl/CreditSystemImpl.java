package com.neusoft.elmboot.domain.impl;

import com.neusoft.elmboot.domain.CreditSystem;
import com.neusoft.elmboot.po.ConsumeCredit;

public class CreditSystemImpl implements CreditSystem {
    @Override
    public int queryEarningCreditBySign(int count, SignCreditRule signCreditRule) {
        if(count<signCreditRule.getDailyCap()){
            return signCreditRule.getRule();
        }
        else
            return 0;
    }

    @Override
    public int queryEarnCreditByRecharge(int money, RechargeCreditRule rechargeCreditRule) {
        return (int) (money*rechargeCreditRule.getRule());
    }

    @Override
    public ConsumeCredit consumeCreditByPaying(Integer money, Integer creditNum, TransferMoneyCreditRule transferMoneyCreditRule) {
        ConsumeCredit consumeCredit =new ConsumeCredit();
        double deductionMoney=transferMoneyCreditRule.getFormula()*creditNum;
        if(deductionMoney<=money){
            consumeCredit.setDeductionMoney(deductionMoney);
            consumeCredit.setCreditNum(creditNum);
            return consumeCredit;
        }
        else {
            deductionMoney=(double)money;
            Integer credit=(int)Math.ceil(money*1.0/transferMoneyCreditRule.getFormula());
            consumeCredit.setCreditNum(credit);
            consumeCredit.setDeductionMoney(deductionMoney);
            return consumeCredit;
        }
    }
}

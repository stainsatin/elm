package com.neusoft.elmboot.service;

import com.neusoft.elmboot.bo.CreditRuleBo;
import com.neusoft.elmboot.entity.ConsumeCredit;
import com.neusoft.elmboot.entity.CreditRecord;
import com.neusoft.elmboot.exception.credit.UserHasSignedException;
import com.neusoft.elmboot.exception.wallet.RechargeFailedException;
import com.neusoft.elmboot.exception.wallet.UserHasNotCreatedWalletIdException;

import java.util.List;

public interface CreditService {
    public Integer queryEarningCreditBySign();

    public Integer earnCreditBySign() throws UserHasSignedException;

    public Integer queryEarnCreditByRecharge(double money);

    public Integer earnCreditByCharge(double money) throws UserHasNotCreatedWalletIdException, RechargeFailedException;

    public Integer earnCreditBySign(String userId, Integer creditNum, Integer transactionId);

    public Integer queryAvailableCredit(String userId);

    public ConsumeCredit consumeCreditByPaying(String userId, Integer money, Integer creditNum);

    public Integer transferMoneyWithCreditConsume(Integer creditNum, Integer id, String userId);

    public List<CreditRecord> queryAllCredit(String userId);

    public Integer updateCreditRule(CreditRuleBo creditRule);

    public List<CreditRuleBo> queryAllCreditRule();
}

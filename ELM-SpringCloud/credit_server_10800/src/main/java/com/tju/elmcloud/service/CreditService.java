package com.tju.elmcloud.service;

import com.tju.elmcloud.po.CreditRecord;
import com.tju.elmcloud.po.CreditRulePo;
import com.tju.elmcloud.vo.ConsumeCredit;

import java.util.List;

public interface CreditService {
    public Integer queryEarningCreditBySign(String userId);
    public Integer earnCreditBySign(String userId,int creditNum);

    public Integer queryEarnCreditByRecharge(String userId,Integer money);
    public Integer earnCreditBySign(String userId,Integer creditNum,Integer transactionId);
    public Integer queryAvailableCredit(String userId);
    public ConsumeCredit consumeCreditByPaying(String userId,Integer money,Integer creditNum);
    public Integer transferMoneyWithCreditConsume(Integer creditNum,Integer id,String userId);
    public List<CreditRecord> queryAllCredit(String userId);
    public Integer updateCreditRule(CreditRulePo creditRule);
    public List<CreditRulePo> queryAllCreditRule();
}

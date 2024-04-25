package com.neusoft.elmboot.controller;


import com.neusoft.elmboot.po.CreditRecord;
import com.neusoft.elmboot.po.CreditRulePo;
import com.neusoft.elmboot.service.CreditService;
import com.neusoft.elmboot.service.VirtualWalletService;
import com.neusoft.elmboot.po.ConsumeCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CreditController")
public class CreditController {

    @Autowired
    CreditService creditService;
    @Autowired
    VirtualWalletService virtualWalletService;
    @RequestMapping("/queryEarningCreditBySign")
    public Integer queryEarningCreditBySign(String userId){
        return creditService.queryEarningCreditBySign(userId);
    }
    @RequestMapping("/earnCreditBySign")
    public Integer earnCreditBySign(String userId,int creditNum){
        return creditService.earnCreditBySign(userId,creditNum);
    }
    @RequestMapping("/queryEarnCreditByRecharge")
    public Integer queryEarnCreditByRecharge(String userId,Integer money){
        return creditService.queryEarnCreditByRecharge(userId,money);
    }
    @RequestMapping("/earnCreditBySignAndRecharge")
    public Integer earnCreditBySignAndRecharge(String userId,Integer money,Integer creditNum,Integer walletId){
        Integer transactionId=virtualWalletService.recharge(walletId,money);
        return creditService.earnCreditBySign(userId,creditNum,transactionId);
    }
    @RequestMapping("/queryAvailableCredit")
    public Integer queryAvailableCredit(String userId){
        return creditService.queryAvailableCredit(userId);
    }
    @RequestMapping("/consumeCreditByPaying")
    public ConsumeCredit consumeCreditByPaying(String userId,Integer money,Integer creditNum){
        return creditService.consumeCreditByPaying(userId,money,creditNum);
    }
    @RequestMapping("/transferMoneyWithCreditConsume")
    public Integer transferMoneyWithCreditConsume(int inputWalletId,int outputWalletId,double money,int orderId,double deductionMoney,int creditNum,String userId){
        int transactionId=virtualWalletService.transferMoneyWithCredit(inputWalletId,outputWalletId,money,money-deductionMoney,orderId);
        return creditService.transferMoneyWithCreditConsume(creditNum,transactionId,userId);
    }
    @RequestMapping("/queryAllCredit")
    public List<CreditRecord> queryAllCredit(String userId){
        return creditService.queryAllCredit(userId);
    }
    @RequestMapping("/updateCreditRule")
    public Integer updateCreditRule(CreditRulePo creditRule){
        CreditRulePo creditRulePo=new CreditRulePo(creditRule.getId(),creditRule.getRuleCode(),creditRule.getType(),creditRule.getPriority(),creditRule.getCredit(),creditRule.getFormula(),creditRule.getDailyCap(),creditRule.getTotCap(),creditRule.getStartTime(),creditRule.getEndTime(),creditRule.getLifespan(),creditRule.getState());
        return creditService.updateCreditRule(creditRulePo);
    }
    @RequestMapping("/queryAllCreditRule")
    public List<CreditRulePo> queryAllCreditRule(){return creditService.queryAllCreditRule();}
}

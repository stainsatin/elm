package com.neusoft.elmboot.controller;


import com.neusoft.elmboot.bo.CreditRuleBo;
import com.neusoft.elmboot.bo.Result;
import com.neusoft.elmboot.entity.ConsumeCredit;
import com.neusoft.elmboot.entity.CreditRecord;
import com.neusoft.elmboot.exception.credit.UserHasSignedException;
import com.neusoft.elmboot.exception.wallet.RechargeFailedException;
import com.neusoft.elmboot.exception.wallet.UserHasNotCreatedWalletIdException;
import com.neusoft.elmboot.service.CreditService;
import com.neusoft.elmboot.service.VirtualWalletService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Resource
    CreditService creditService;
    @Resource
    VirtualWalletService virtualWalletService;

    @GetMapping("/sign")
    public Result queryEarningCreditBySign() {
        return Result.success(creditService.queryEarningCreditBySign());
    }

    @PostMapping("/sign")
    public Result earnCreditBySign() throws UserHasSignedException {
        return Result.success(creditService.earnCreditBySign());
    }

    @GetMapping("/recharge")
    public Result queryEarnCreditByRecharge(double money) {
        return Result.success(creditService.queryEarnCreditByRecharge(money));
    }

    @PostMapping("/recharge")
    public Result earnCreditByCharge(double money) throws UserHasNotCreatedWalletIdException, RechargeFailedException {
        return Result.success(creditService.earnCreditByCharge(money));
    }

    @RequestMapping("/earnCreditBySignAndRecharge")
    public Integer earnCreditBySignAndRecharge(String userId, Integer money, Integer creditNum, Integer walletId) throws UserHasNotCreatedWalletIdException {
        Integer transactionId = virtualWalletService.recharge(money);
        return creditService.earnCreditBySign(userId, creditNum, transactionId);
    }

    @RequestMapping("/queryAvailableCredit")
    public Integer queryAvailableCredit(String userId) {
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
    public List<CreditRecord> queryAllCredit(String userId) {
        return creditService.queryAllCredit(userId);
    }

    @RequestMapping("/updateCreditRule")
    public Integer updateCreditRule(CreditRuleBo creditRule) {
        CreditRuleBo creditRuleBo = new CreditRuleBo(creditRule.getId(), creditRule.getRuleCode(), creditRule.getType(), creditRule.getPriority(), creditRule.getCredit(), creditRule.getFormula(), creditRule.getDailyCap(), creditRule.getTotCap(), creditRule.getStartTime(), creditRule.getEndTime(), creditRule.getLifespan(), creditRule.getState());
        return creditService.updateCreditRule(creditRuleBo);
    }

    @RequestMapping("/queryAllCreditRule")
    public List<CreditRuleBo> queryAllCreditRule() {
        return creditService.queryAllCreditRule();
    }
}

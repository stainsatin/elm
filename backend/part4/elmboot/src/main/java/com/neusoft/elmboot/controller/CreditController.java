package com.neusoft.elmboot.controller;


import com.neusoft.elmboot.bo.CreditRuleBo;
import com.neusoft.elmboot.bo.Result;
import com.neusoft.elmboot.exception.credit.UserHasSignedException;
import com.neusoft.elmboot.exception.order.OrderHasPayedException;
import com.neusoft.elmboot.exception.order.OrderIdUserIdNotMatchedException;
import com.neusoft.elmboot.exception.wallet.BalanceRemainNotEnoughException;
import com.neusoft.elmboot.exception.wallet.PayOrdersFailedException;
import com.neusoft.elmboot.exception.wallet.RechargeFailedException;
import com.neusoft.elmboot.exception.wallet.UserHasNotCreatedWalletIdException;
import com.neusoft.elmboot.service.CreditService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Resource
    CreditService creditService;

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

    @GetMapping
    public Result queryAvailableCredit() {
        return Result.success(creditService.queryAvailableCredit());
    }

    @GetMapping("/consume")
    public Result consumeCreditByPaying(double money) {
        return Result.success(creditService.consumeCreditByPaying(money));
    }

    @PostMapping("/consume")
    public Result transferMoneyWithCreditConsume(Integer orderId) throws BalanceRemainNotEnoughException, UserHasNotCreatedWalletIdException, PayOrdersFailedException, OrderHasPayedException, OrderIdUserIdNotMatchedException {
        return Result.success(creditService.transferMoneyWithCreditConsume(orderId));
    }


    @PutMapping("/rule")
    public Result updateCreditRule(CreditRuleBo creditRule) {
        CreditRuleBo creditRuleBo = new CreditRuleBo(creditRule.getId(), creditRule.getRuleCode(), creditRule.getType(), creditRule.getPriority(), creditRule.getCredit(), creditRule.getFormula(), creditRule.getDailyCap(), creditRule.getTotCap(), creditRule.getStartTime(), creditRule.getEndTime(), creditRule.getLifespan(), creditRule.getState());
        return Result.success(creditService.updateCreditRule(creditRuleBo));
    }

    @GetMapping("/rule")
    public Result queryAllCreditRule() {
        return
                Result.success(creditService.queryAllCreditRule());
    }
}

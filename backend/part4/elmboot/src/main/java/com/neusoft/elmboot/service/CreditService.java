package com.neusoft.elmboot.service;

import com.neusoft.elmboot.bo.CreditRuleBo;
import com.neusoft.elmboot.entity.ConsumeCredit;
import com.neusoft.elmboot.entity.CreditRecord;
import com.neusoft.elmboot.exception.credit.UserHasSignedException;
import com.neusoft.elmboot.exception.order.OrderHasPayedException;
import com.neusoft.elmboot.exception.order.OrderIdUserIdNotMatchedException;
import com.neusoft.elmboot.exception.wallet.BalanceRemainNotEnoughException;
import com.neusoft.elmboot.exception.wallet.PayOrdersFailedException;
import com.neusoft.elmboot.exception.wallet.RechargeFailedException;
import com.neusoft.elmboot.exception.wallet.UserHasNotCreatedWalletIdException;

import java.util.List;

public interface CreditService {
    public Integer queryEarningCreditBySign();

    public Integer earnCreditBySign() throws UserHasSignedException;

    public Integer queryEarnCreditByRecharge(double money);

    public Integer earnCreditByCharge(double money) throws UserHasNotCreatedWalletIdException, RechargeFailedException;

    public Integer earnCreditBySign(String userId, Integer creditNum, Integer transactionId);

    public Integer queryAvailableCredit();

    public ConsumeCredit consumeCreditByPaying(double money);

    public Integer transferMoneyWithCreditConsume(Integer orderId) throws BalanceRemainNotEnoughException, UserHasNotCreatedWalletIdException, PayOrdersFailedException, OrderHasPayedException, OrderIdUserIdNotMatchedException;

    public List<CreditRecord> queryAllCredit(String userId);

    public Integer updateCreditRule(CreditRuleBo creditRule);

    public List<CreditRuleBo> queryAllCreditRule();
}

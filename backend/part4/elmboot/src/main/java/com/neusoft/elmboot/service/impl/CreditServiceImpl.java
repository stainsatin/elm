package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.bo.CreditRuleBo;
import com.neusoft.elmboot.bo.TransactionBo;
import com.neusoft.elmboot.bo.VirtualWalletBo;
import com.neusoft.elmboot.creditRuleMap.CreditRuleMap;
import com.neusoft.elmboot.domain.CreditSystem;
import com.neusoft.elmboot.domain.Rule;
import com.neusoft.elmboot.domain.VirtualWallet;
import com.neusoft.elmboot.domain.impl.*;
import com.neusoft.elmboot.entity.ConsumeCredit;
import com.neusoft.elmboot.entity.CreditRecord;
import com.neusoft.elmboot.entity.UsableCredit;
import com.neusoft.elmboot.exception.credit.UserHasSignedException;
import com.neusoft.elmboot.exception.wallet.RechargeFailedException;
import com.neusoft.elmboot.exception.wallet.UserHasNotCreatedWalletIdException;
import com.neusoft.elmboot.mapper.*;
import com.neusoft.elmboot.service.CreditService;
import com.neusoft.elmboot.util.CommonUtil;
import com.neusoft.elmboot.util.UserUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {

    @Resource
    private CreditRuleMapper creditRuleMapper;
    @Resource
    private CreditRecordMapper creditRecordMapper;
    @Resource
    private VirtualWalletMapper virtualWalletMapper;
    @Resource
    private TransactionMapper transactionMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public Integer queryEarningCreditBySign() {
        Integer ruleId = 1;
        String time = CommonUtil.getCurrentDate();
        String userId = UserUtil.getUserId();
        String today = time.substring(0, time.indexOf(' ')).trim();
        int count = creditRecordMapper.todaySignRecord(userId, ruleId, today);
        SignCreditRule signCreditRule = null;
        signCreditRule = (SignCreditRule) creditRuleMap.getRule(ruleId);
        CreditSystem creditSystem = new CreditSystemImpl();
        return creditSystem.queryEarningCreditBySign(count, signCreditRule);
    }

    @Override
    @Transactional
    public Integer earnCreditBySign() throws UserHasSignedException {
        String createTime = CommonUtil.getCurrentDate();
        SignCreditRule rule = (SignCreditRule) creditRuleMap.getRule(1);
        String endTime = CommonUtil.getEndTime(rule.getLifeSpan());
        String userId = UserUtil.getUserId();
        Integer creditNum = rule.getCredit();
        Integer check = this.queryEarningCreditBySign();
        if (check == 0) throw new UserHasSignedException();
        CreditRecord creditRecord = new CreditRecord(1, userId, creditNum, createTime, endTime);
        int done1 = creditRecordMapper.insertSignCreditRecord(creditRecord);
        int done2 = creditRecordMapper.insertUsableCredit(userId, creditRecord.getId(), creditNum, createTime, endTime);
        if (done2 == 1 && done1 == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Integer queryEarnCreditByRecharge(double money) {
        Integer ruleId = 2;
        RechargeCreditRule rechargeCreditRule = null;
        rechargeCreditRule = (RechargeCreditRule) creditRuleMap.getRule(ruleId);
        int count = (int) money;
        CreditSystem creditSystem = new CreditSystemImpl();
        return creditSystem.queryEarnCreditByRecharge(count, rechargeCreditRule);
    }

    @Override
    @Transactional
    public Integer earnCreditByCharge(double money) throws UserHasNotCreatedWalletIdException, RechargeFailedException {
        Integer walletId = userMapper.getWalletIdByUserId(UserUtil.getUserId());
        if (walletId == null) {
            throw new UserHasNotCreatedWalletIdException();
        }
        double balance = virtualWalletMapper.queryBalance(walletId);
        VirtualWallet virtualWallet = new VirtualWalletImpl(walletId, balance);
        if (virtualWallet.increaseBalance(money) == 1) {
            VirtualWalletBo virtualWalletBo = new VirtualWalletBo(walletId, virtualWallet.getBalance());
            TransactionBo transactionPo = new TransactionBo(CommonUtil.getCurrentDate(), money, 0, walletId, null);
            int done1 = virtualWalletMapper.updateBalance(virtualWalletBo);
            int done2 = transactionMapper.writeTransaction(transactionPo);
            if (done2 == 1 && done1 == 1) {
                Integer transactionId = transactionPo.getTransactionId();
                Integer creditNum = this.queryEarnCreditByRecharge(money);
                Integer ruleId = 2;
                RechargeCreditRule rechargeCreditRule = null;
                rechargeCreditRule = (RechargeCreditRule) creditRuleMap.getRule(ruleId);
                String createTime = CommonUtil.getCurrentDate();
                String endTime = CommonUtil.getEndTime(rechargeCreditRule.getLifeSpan());
                String userId = UserUtil.getUserId();
                //int ruleCode,String userId,int credit,String createTime,String expiredTime,int eventId
                CreditRecord creditRecord = new CreditRecord(2, userId, creditNum, createTime, endTime, transactionId);
                int done3 = creditRecordMapper.insertRechargeCreditRecord(creditRecord);
                int done4 = creditRecordMapper.insertUsableCredit(userId, creditRecord.getId(), creditNum, createTime, endTime);
                if (done3 == 1 && done4 == 1) {
                    return 1;
                } else {
                    throw new RechargeFailedException();
                }
            } else throw new RechargeFailedException();
        } else {
            throw new RechargeFailedException();
        }
    }

    @Override
    @Transactional
    public Integer earnCreditBySign(String userId, Integer creditNum, Integer transactionId) {
        String createTime = CommonUtil.getCurrentDate();
        int lifeSpan = 0;
        synchronized (creditRuleMap) {
            lifeSpan = ((RechargeCreditRule) (creditRuleMap.getRule(2))).getLifeSpan();
        }
        String endTime = CommonUtil.getEndTime(lifeSpan);
        CreditRecord creditRecord = new CreditRecord(2, userId, creditNum, createTime, endTime, transactionId);
        int done1 = creditRecordMapper.insertSignCreditRecord(creditRecord);
        int done2 = creditRecordMapper.insertUsableCredit(userId, creditRecord.getId(), creditNum, createTime, endTime);
        if (done2 == 1 && done1 == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Integer queryAvailableCredit(String userId) {
        creditRecordMapper.updataQueryAvailableCredit(userId, CommonUtil.getCurrentDate());
        int availableCredit = creditRecordMapper.queryAvailableCredit(userId, CommonUtil.getCurrentDate());
        return availableCredit;
    }

    @Override
    public ConsumeCredit consumeCreditByPaying(String userId, Integer money, Integer creditNum) {
        Integer ruleId = 3;
        TransferMoneyCreditRule transferMoneyCreditRule = null;
        synchronized (creditRuleMap) {
            transferMoneyCreditRule = (TransferMoneyCreditRule) creditRuleMap.getRule(ruleId);
            if (transferMoneyCreditRule == null) {
                CreditRuleBo creditRuleBo = creditRuleMapper.getRule(ruleId);
                double formula = creditRuleBo.getFormula();
                transferMoneyCreditRule = new TransferMoneyCreditRule(formula);
                creditRuleMap.writeMap(ruleId, transferMoneyCreditRule);
            }
        }
        CreditSystem creditSystem = new CreditSystemImpl();
        return creditSystem.consumeCreditByPaying(money, creditNum, transferMoneyCreditRule);
    }

    @Override
    @Transactional
    public Integer transferMoneyWithCreditConsume(Integer creditNum, Integer id, String userId) {
        Integer ruleId = 3;
        creditNum = -creditNum;
        CreditRecord creditRecord = new CreditRecord(ruleId, userId, creditNum, CommonUtil.getCurrentDate(), id);
        creditRecordMapper.insertSignCreditRecord(creditRecord);
        int recordId = creditRecord.getId();
        List<UsableCredit> list = creditRecordMapper.listUsableCredit(userId);
        Iterator iterator = list.iterator();
        creditNum = -creditNum;
        while (creditNum > 0) {
            UsableCredit usableCredit = (UsableCredit) iterator.next();
            if (creditNum >= usableCredit.getCredit()) {
                creditNum = creditNum - usableCredit.getCredit();
                creditRecordMapper.consumeCredit(usableCredit.getId());
                creditRecordMapper.insertReducecredit(userId, recordId, usableCredit.getId(), usableCredit.getCredit(), usableCredit.getCreateTime(), usableCredit.getExpiredTime());
            } else {
                creditRecordMapper.insertReducecredit(userId, recordId, usableCredit.getId(), creditNum, usableCredit.getCreateTime(), usableCredit.getExpiredTime());
                creditRecordMapper.updateCredit(usableCredit.getId(), usableCredit.getCredit() - creditNum);
                creditNum = 0;
            }
        }
        if (creditNum == 0)
            return 1;
        else
            return 0;
    }

    @Override
    public List<CreditRecord> queryAllCredit(String userId) {
        List<CreditRecord> list = creditRecordMapper.queryAllCredit(userId);
        return list;
    }

    @Resource
    private CreditRuleMap creditRuleMap;

    @Override
    public Integer updateCreditRule(CreditRuleBo creditRule) {
        Integer ruleId = creditRule.getId();
        Rule rule = creditRuleMap.getRule(ruleId);
        switch (ruleId) {
            case 1: {
                SignCreditRule signCreditRule = (SignCreditRule) rule;
                System.out.println(signCreditRule);
                signCreditRule.setLifeSpan(creditRule.getLifespan());
                signCreditRule.setCredit(creditRule.getCredit());
                signCreditRule.setDailyCap(creditRule.getDailyCap());
                creditRuleMap.writeMap(1, signCreditRule);
            }
            case 2 : {
                RechargeCreditRule rechargeCreditRule = (RechargeCreditRule) rule;
                rechargeCreditRule.setLifeSpan(creditRule.getLifespan());
                rechargeCreditRule.setFormula(creditRule.getFormula());
                creditRuleMap.writeMap(2, rechargeCreditRule);
            }
            case 3 : {
                rule = (TransferMoneyCreditRule) rule;
                ((TransferMoneyCreditRule) rule).setFormula(creditRule.getFormula());
                creditRuleMap.writeMap(3, rule);
            }
        }
        return creditRuleMapper.updateCreditRule(creditRule);
    }

    public List<CreditRuleBo> queryAllCreditRule() {
        return creditRuleMapper.queryAllCreditRule();
    }
}

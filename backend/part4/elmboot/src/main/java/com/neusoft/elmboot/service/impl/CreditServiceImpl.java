package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.creditRuleMap.CreditRuleMap;
import com.neusoft.elmboot.domain.CreditSystem;
import com.neusoft.elmboot.domain.Rule;
import com.neusoft.elmboot.domain.impl.CreditSystemImpl;
import com.neusoft.elmboot.domain.impl.RechargeCreditRule;
import com.neusoft.elmboot.domain.impl.SignCreditRule;
import com.neusoft.elmboot.domain.impl.TransferMoneyCreditRule;
import com.neusoft.elmboot.mapper.CreditRecordMapper;
import com.neusoft.elmboot.mapper.CreditRuleMapper;
import com.neusoft.elmboot.po.CreditRecord;
import com.neusoft.elmboot.po.CreditRulePo;
import com.neusoft.elmboot.po.UsableCredit;
import com.neusoft.elmboot.service.CreditService;
import com.neusoft.elmboot.util.CommonUtil;
import com.neusoft.elmboot.po.ConsumeCredit;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRuleMapper creditRuleMapper;
    @Autowired
    private CreditRecordMapper creditRecordMapper;

    @Override
    public Integer queryEarningCreditBySign(String userId) {
        Integer ruleId = 1;
        String time = CommonUtil.getCurrentDate();
        String today = time.substring(0, time.indexOf(' ')).trim();
        int count = creditRecordMapper.todaySignRecord(userId, ruleId, today);
        SignCreditRule signCreditRule = null;
        synchronized (creditRuleMap) {
            signCreditRule = (SignCreditRule) creditRuleMap.getRule(ruleId);
            if (signCreditRule == null) {
                CreditRulePo creditRulePo = creditRuleMapper.getRule(ruleId);
                int credit = creditRulePo.getCredit();
                int lifeSpan = creditRulePo.getLifespan();
                int totCap = creditRulePo.getDailyCap();
                signCreditRule = new SignCreditRule(lifeSpan, credit, totCap);
                creditRuleMap.writeMap(ruleId, signCreditRule);
            }
        }
        CreditSystem creditSystem = new CreditSystemImpl();
        return creditSystem.queryEarningCreditBySign(count, signCreditRule);
    }

    @Override
    @Transactional
    public Integer earnCreditBySign(String userId, int creditNum) {
        String createTime = CommonUtil.getCurrentDate();
        int lifeSpan = 0;
        SignCreditRule rule = (SignCreditRule)creditRuleMap.getRule(1);
        String endTime = CommonUtil.getEndTime(rule.getLifeSpan());
        CreditRecord creditRecord = new CreditRecord(1, userId, creditNum, createTime, endTime,-1);
        int done1 = creditRecordMapper.insertCreditRecord(creditRecord);
        int done2 = creditRecordMapper.insertUsableCredit(userId, creditRecord.getId(), creditNum, createTime, endTime);
        System.out.println(done1);
        System.out.println(done2);
        if (done2 == 1 && done1 == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Integer queryEarnCreditByRecharge(String userId, Integer money) {
        Integer ruleId = 2;
        RechargeCreditRule rechargeCreditRule = null;
        synchronized (creditRuleMap) {
            rechargeCreditRule = (RechargeCreditRule) creditRuleMap.getRule(ruleId);
            if (rechargeCreditRule == null) {
                CreditRulePo creditRulePo = creditRuleMapper.getRule(ruleId);
                double formula = creditRulePo.getFormula();
                int lifeSpan = creditRulePo.getLifespan();
                rechargeCreditRule = new RechargeCreditRule(lifeSpan, formula);
                creditRuleMap.writeMap(ruleId, rechargeCreditRule);
            }
        }
        CreditSystem creditSystem = new CreditSystemImpl();
        return creditSystem.queryEarnCreditByRecharge(money, rechargeCreditRule);
    }

    @Override
    public Integer earnCreditBySign(String userId, Integer creditNum, Integer transactionId) {
        String createTime = CommonUtil.getCurrentDate();
        int lifeSpan = 0;
        synchronized (creditRuleMap) {
            lifeSpan = ((RechargeCreditRule) (creditRuleMap.getRule(2))).getLifeSpan();
        }
        String endTime = CommonUtil.getEndTime(lifeSpan);
        CreditRecord creditRecord = new CreditRecord(2, userId, creditNum, createTime, endTime, transactionId);
        int done1 = creditRecordMapper.insertCreditRecord(creditRecord);
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
                CreditRulePo creditRulePo = creditRuleMapper.getRule(ruleId);
                double formula = creditRulePo.getFormula();
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
        creditRecordMapper.insertCreditRecord(creditRecord);
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
    public Integer updateCreditRule(CreditRulePo creditRule) {
        Integer ruleId = creditRule.getId();
        Rule rule = creditRuleMap.getRule(ruleId);
        switch (ruleId) {
            case 1 : {
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

    public List<CreditRulePo> queryAllCreditRule() {
        return creditRuleMapper.queryAllCreditRule();
    }
}

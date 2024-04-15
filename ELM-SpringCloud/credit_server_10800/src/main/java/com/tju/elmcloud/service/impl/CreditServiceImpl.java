package com.tju.elmcloud.service.impl;

import com.tju.elmcloud.creditRuleMap.CreditRuleMap;
import com.tju.elmcloud.domain.CreditSystem;
import com.tju.elmcloud.domain.Rule;
import com.tju.elmcloud.domain.impl.CreditSystemImpl;
import com.tju.elmcloud.domain.impl.RechargeCreditRule;
import com.tju.elmcloud.domain.impl.SignCreditRule;
import com.tju.elmcloud.domain.impl.TransferMoneyCreditRule;
import com.tju.elmcloud.mapper.CreditRecordMapper;
import com.tju.elmcloud.mapper.CreditRuleMapper;
import com.tju.elmcloud.po.CreditRecord;
import com.tju.elmcloud.po.CreditRulePo;
import com.tju.elmcloud.po.UsableCredit;
import com.tju.elmcloud.service.CreditService;
import com.tju.elmcloud.util.CommonUtil;
import com.tju.elmcloud.vo.ConsumeCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Integer ruleId=1;
        String time= CommonUtil.getCurrentDate();
        String today=time.substring(0,time.indexOf(' ')).trim();
        int count=creditRecordMapper.todaySignRecord(userId,ruleId,today);
        CreditRuleMap creditRuleMap=CreditRuleMap.getRuleMap();
        SignCreditRule signCreditRule=null;
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
        CreditSystem creditSystem=new CreditSystemImpl();
        return  creditSystem.queryEarningCreditBySign(count,signCreditRule);
    }

    @Override
    @Transactional
    public Integer earnCreditBySign(String userId, int creditNum) {
        System.out.println("serivice: "+userId);
        System.out.println("serivice: "+creditNum);
        String createTime=CommonUtil.getCurrentDate();
        CreditRuleMap creditRuleMap=CreditRuleMap.getRuleMap();
        int lifeSpan=0;
        synchronized (creditRuleMap) {
            lifeSpan = ((SignCreditRule) creditRuleMap.getRule(1)).getLifeSpan();
        }
        String endTime=CommonUtil.getEndTime(lifeSpan);
        CreditRecord creditRecord=new CreditRecord(1,userId,creditNum,createTime,endTime);
        int done1=creditRecordMapper.insertCreditRecord(creditRecord);
        int done2=creditRecordMapper.insertUsableCredit(userId,creditRecord.getId(),creditNum,createTime,endTime);
        if (done2==1&&done1==1){
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public Integer queryEarnCreditByRecharge(String userId, Integer money) {

        Integer ruleId=2;
        CreditRuleMap creditRuleMap=CreditRuleMap.getRuleMap();
        RechargeCreditRule rechargeCreditRule=null;
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
        CreditSystem creditSystem=new CreditSystemImpl();
        return creditSystem.queryEarnCreditByRecharge(money,rechargeCreditRule);
    }

    @Override
    public Integer earnCreditBySign(String userId, Integer creditNum, Integer transactionId) {
        String createTime=CommonUtil.getCurrentDate();
        int lifeSpan=0;
        CreditRuleMap creditRuleMap=CreditRuleMap.getRuleMap();
        synchronized (creditRuleMap){
            lifeSpan=((RechargeCreditRule)(creditRuleMap.getRule(2))).getLifeSpan();
        }
        String endTime=CommonUtil.getEndTime(lifeSpan);
        CreditRecord creditRecord=new CreditRecord(2,userId,creditNum,createTime,endTime,transactionId);
        int done1=creditRecordMapper.insertCreditRecord(creditRecord);
        int done2=creditRecordMapper.insertUsableCredit(userId,creditRecord.getId(),creditNum,createTime,endTime);
        if (done2==1&&done1==1){
            return 1;
        }
        else {
            return 0;
        }
    }

    @Override
    public Integer queryAvailableCredit(String userId) {
        creditRecordMapper.updataQueryAvailableCredit(userId,CommonUtil.getCurrentDate());
        Integer availableCredit=creditRecordMapper.queryAvailableCredit(userId,CommonUtil.getCurrentDate());
        if(availableCredit==null)return 0;
        return availableCredit;
    }

    @Override
    public ConsumeCredit consumeCreditByPaying(String userId, Integer money, Integer creditNum) {
        Integer ruleId=3;
        CreditRuleMap creditRuleMap=CreditRuleMap.getRuleMap();
        TransferMoneyCreditRule transferMoneyCreditRule=null;
        synchronized (creditRuleMap){
            transferMoneyCreditRule=(TransferMoneyCreditRule) creditRuleMap.getRule(ruleId);
            if(transferMoneyCreditRule==null){
                CreditRulePo creditRulePo = creditRuleMapper.getRule(ruleId);
                double formula=creditRulePo.getFormula();
                transferMoneyCreditRule=new TransferMoneyCreditRule(formula);
                creditRuleMap.writeMap(ruleId,transferMoneyCreditRule);
            }
        }
        CreditSystem creditSystem=new CreditSystemImpl();
        return creditSystem.consumeCreditByPaying(money,creditNum,transferMoneyCreditRule);
    }

    @Override
    @Transactional
    public Integer transferMoneyWithCreditConsume(Integer creditNum, Integer id, String userId) {
        Integer ruleId=3;
        creditNum=-creditNum;
        CreditRecord creditRecord=new CreditRecord(ruleId,userId,creditNum,CommonUtil.getCurrentDate(),id);
        creditRecordMapper.insertCreditRecord(creditRecord);
        int recordId=creditRecord.getId();
        List<UsableCredit> list=creditRecordMapper.listUsableCredit(userId);
        Iterator iterator=list.iterator();
        creditNum=-creditNum;
        while (creditNum>0){
            UsableCredit usableCredit=(UsableCredit) iterator.next();
            if (creditNum>=usableCredit.getCredit()){
                creditNum=creditNum-usableCredit.getCredit();
                creditRecordMapper.consumeCredit(usableCredit.getId());
                creditRecordMapper.insertReducecredit(userId,recordId,usableCredit.getId(),usableCredit.getCredit(),usableCredit.getCreateTime(),usableCredit.getExpiredTime());
            } else {
                creditRecordMapper.insertReducecredit(userId,recordId,usableCredit.getId(),creditNum,usableCredit.getCreateTime(),usableCredit.getExpiredTime());
                creditRecordMapper.updateCredit(usableCredit.getId(),usableCredit.getCredit()-creditNum);
                creditNum=0;
            }
        }
        if(creditNum==0)
            return 1;
        else
            return 0;
    }

    @Override
    public List<CreditRecord> queryAllCredit(String userId) {
        List<CreditRecord> list=creditRecordMapper.queryAllCredit(userId);
        return list;
    }

    @Override
    public Integer updateCreditRule(CreditRulePo creditRule) {
        CreditRuleMap creditRuleMap=CreditRuleMap.getRuleMap();
        synchronized (creditRuleMap){
            Integer ruleId=creditRule.getId();
            Rule rule=creditRuleMap.getRule(ruleId);
            if (ruleId == 1) {
                if(rule==null){
                    rule = new SignCreditRule(creditRule.getLifespan(),creditRule.getCredit(),creditRule.getDailyCap());
                }
                else {
                    rule = (SignCreditRule) rule;
                    ((SignCreditRule) rule).setLifeSpan(creditRule.getLifespan());
                    ((SignCreditRule) rule).setCredit(creditRule.getCredit());
                    ((SignCreditRule) rule).setDailyCap(creditRule.getDailyCap());
                }
                creditRuleMap.writeMap(1, rule);
            }
            else if (ruleId==2){
                if(rule == null){
                    rule=new RechargeCreditRule(creditRule.getLifespan(),creditRule.getFormula());
                }
                else {
                    rule=(RechargeCreditRule)rule;
                    ((RechargeCreditRule) rule).setLifeSpan(creditRule.getLifespan());
                    ((RechargeCreditRule) rule).setFormula(creditRule.getFormula());
                }
                creditRuleMap.writeMap(2,rule);
            }
            else if(ruleId==3){
                if(rule==null){
                    rule=new TransferMoneyCreditRule(creditRule.getFormula());
                }
                else {
                    rule = (TransferMoneyCreditRule) rule;
                    ((TransferMoneyCreditRule) rule).setFormula(creditRule.getFormula());
                }
                creditRuleMap.writeMap(3,rule);
            }
        }
        return creditRuleMapper.updateCreditRule(creditRule);
    }

    public List<CreditRulePo> queryAllCreditRule(){
        return creditRuleMapper.queryAllCreditRule();
    }
}

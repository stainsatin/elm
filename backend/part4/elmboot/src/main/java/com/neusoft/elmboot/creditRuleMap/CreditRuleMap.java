package com.neusoft.elmboot.creditRuleMap;

import com.neusoft.elmboot.domain.Rule;
import com.neusoft.elmboot.domain.impl.RechargeCreditRule;
import com.neusoft.elmboot.domain.impl.SignCreditRule;
import com.neusoft.elmboot.domain.impl.TransferMoneyCreditRule;
import com.neusoft.elmboot.mapper.RuleMapper;
import com.neusoft.elmboot.po.CreditRulePo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CreditRuleMap {
    private static Map<Integer, Rule> ruleMap=new ConcurrentHashMap<>();

    @Resource
    private RuleMapper ruleMapper;

    public Rule getRule(Integer ruleId){
        if(ruleMap.containsKey(ruleId)){
            return ruleMap.get(ruleId);
        }
        else {
            CreditRulePo rulePo = ruleMapper.getRule(ruleId);
            Rule rule = null;
            switch (rulePo.getId()){
                case 1:{
                    rule=new SignCreditRule(rulePo.getLifespan(),rulePo.getCredit(),rulePo.getDailyCap());
                    ruleMap.put(ruleId,rule);
                }
                case 2:{
                    rule=new RechargeCreditRule(rulePo.getLifespan(),rulePo.getFormula());
                    ruleMap.put(ruleId,rule);
                }
                case 3:{
                    rule = new TransferMoneyCreditRule(rulePo.getFormula());
                    ruleMap.put(ruleId,rule);
                }
            }
            return rule;
        }
    }

    public void writeMap(Integer ruleId,Rule creditRule){
        ruleMap.put(ruleId,creditRule);
    }
}

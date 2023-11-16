package com.neusoft.elmboot.creditRuleMap;

import com.neusoft.elmboot.bo.CreditRuleBo;
import com.neusoft.elmboot.domain.Rule;
import com.neusoft.elmboot.domain.impl.RechargeCreditRule;
import com.neusoft.elmboot.domain.impl.SignCreditRule;
import com.neusoft.elmboot.domain.impl.TransferMoneyCreditRule;
import com.neusoft.elmboot.mapper.RuleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CreditRuleMap {
    // 饿汉式创建单例releMap
    private static Map<Integer, Rule> ruleMap = new ConcurrentHashMap<>();

    @Resource
    private RuleMapper ruleMapper;

    private CreditRuleMap() {
    }

    public static Map<Integer, Rule> getRuleMap() {
        return ruleMap;
    }

    public Rule getRule(Integer ruleId) {
        Map<Integer, Rule> instance = getRuleMap();
        if (instance.containsKey(ruleId)) {
            return instance.get(ruleId);
        } else {
            CreditRuleBo rulePo = ruleMapper.getRule(ruleId);
            Rule rule = null;
            System.out.println("id:");
            System.out.println(rulePo.getId());
            switch (rulePo.getId()) {
                case 1: {
                    rule = new SignCreditRule(rulePo.getLifespan(), rulePo.getCredit(), rulePo.getDailyCap());
                    instance.put(ruleId, rule);
                    break;
                }
                case 2: {
                    rule = new RechargeCreditRule(rulePo.getLifespan(), rulePo.getFormula());
                    instance.put(ruleId, rule);
                    break;
                }
                case 3: {
                    rule = new TransferMoneyCreditRule(rulePo.getFormula());
                    instance.put(ruleId, rule);
                    break;
                }
            }
            return rule;
        }
    }

    public void writeMap(Integer ruleId,Rule creditRule){
        ruleMap.put(ruleId,creditRule);
    }
}

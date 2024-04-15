package com.tju.elmcloud.creditRuleMap;

import com.tju.elmcloud.domain.Rule;

import java.util.HashMap;
import java.util.Map;

public class CreditRuleMap {
    private Map<Integer, Rule> ruleMap=null;

    private static CreditRuleMap creditRuleMap=null;

    private CreditRuleMap(){

    }

    public static synchronized CreditRuleMap getRuleMap(){
        if(creditRuleMap==null)
            creditRuleMap=new CreditRuleMap();
        return creditRuleMap;
    }

    public Rule getRule(Integer ruleId){
        if(ruleMap==null)
            ruleMap=new HashMap<>();
        if(ruleMap.containsKey(ruleId)){
            return ruleMap.get(ruleId);
        }
        else {
            return null;
        }
    }

    public void writeMap(Integer ruleId,Rule creditRule){
        ruleMap.put(ruleId,creditRule);
    }
}

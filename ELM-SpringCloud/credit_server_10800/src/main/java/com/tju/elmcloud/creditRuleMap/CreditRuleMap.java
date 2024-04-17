package com.tju.elmcloud.creditRuleMap;

import com.tju.elmcloud.domain.Rule;
import com.tju.elmcloud.domain.impl.RechargeCreditRule;
import com.tju.elmcloud.domain.impl.SignCreditRule;
import com.tju.elmcloud.domain.impl.TransferMoneyCreditRule;
import com.tju.elmcloud.mapper.CreditRuleMapper;
import com.tju.elmcloud.po.CreditRulePo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CreditRuleMap {
    private Map<Integer, Rule> ruleMap;

    private static volatile CreditRuleMap creditRuleMap; // 单例实例，使用 volatile 确保多线程环境下的可见性
    private CreditRuleMapper creditRuleMapper;

    private CreditRuleMap() {
        ruleMap = new ConcurrentHashMap<>();
    }

    public static CreditRuleMap getRuleMap() {
        if (creditRuleMap == null) {
            synchronized (CreditRuleMap.class) {
                if (creditRuleMap == null) {
                    creditRuleMap = new CreditRuleMap();
                }
            }
        }
        return creditRuleMap;
    }

    public Rule getRule(Integer ruleId) {
        if (ruleMap == null)
            ruleMap = new ConcurrentHashMap<>();
        if (ruleMap.containsKey(ruleId)) {
            return ruleMap.get(ruleId);
        } else {
            return null;
        }
    }

    public void writeMap(Integer ruleId, Rule creditRule) {
        ruleMap.put(ruleId, creditRule); // 将规则与 ID 关联
    }
}

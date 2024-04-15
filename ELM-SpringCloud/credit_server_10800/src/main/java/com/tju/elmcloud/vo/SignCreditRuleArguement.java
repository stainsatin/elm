package com.tju.elmcloud.vo;

import com.tju.elmcloud.util.CommonUtil;

public class SignCreditRuleArguement extends CreditRuleArguement {
    private String time;
    public SignCreditRuleArguement(String userId,Integer ruleId){
        super(userId,ruleId);
        this.time= CommonUtil.getCurrentDate();
    }

    public String getTime() {
        return time;
    }
}

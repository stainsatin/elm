package com.tju.elmcloud.vo;

public class CreditRuleArguement {
    private String userId;
    private  int ruleId;

    public String getUserId() {
        return userId;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public CreditRuleArguement(String userId, int ruleId){
        this.ruleId=ruleId;
        this.userId=userId;
    }
}

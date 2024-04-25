package com.neusoft.elmboot.domain.impl;

import com.neusoft.elmboot.domain.CreditRule;

public class SignCreditRule extends CreditRule {
    private int credit;
    private int dailyCap;

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getDailyCap() {
        return dailyCap;
    }

    public void setDailyCap(int dailyCap) {
        this.dailyCap = dailyCap;
    }

    public SignCreditRule(int lifespan, int credit, int dailyCap){
        super(lifespan);
        this.credit=credit;
        this.dailyCap=dailyCap;
    }
    @Override
    public  Integer getRule() {
        return credit;
    }
}

package com.tju.elmcloud.domain;

public abstract class CreditRule extends Rule{
    private Integer lifeSpan;
    public CreditRule(int lifeSpan){
        this.lifeSpan=lifeSpan;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(Integer lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public abstract Object getRule();
}

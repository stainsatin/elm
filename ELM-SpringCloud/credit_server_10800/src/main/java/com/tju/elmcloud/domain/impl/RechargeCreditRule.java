package com.tju.elmcloud.domain.impl;

import com.tju.elmcloud.domain.CreditRule;

public class RechargeCreditRule extends CreditRule {

    private double formula;
    public RechargeCreditRule(int lifeSpan,double formula) {
        super(lifeSpan);
        this.formula=formula;
    }

    public double getFormula() {
        return formula;
    }

    public void setFormula(double formula) {
        this.formula = formula;
    }

    @Override
    public Double getRule() {
        return this.formula;
    }
}

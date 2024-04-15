package com.tju.elmcloud.domain;

public abstract class ConsumeCreditRule extends Rule{
    private double formula;
    public ConsumeCreditRule(double formula){
        this.formula=formula;
    }

    public double getFormula() {
        return formula;
    }

    public void setFormula(double formula) {
        this.formula = formula;
    }

    public abstract Object getRule();
}

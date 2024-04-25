package com.neusoft.elmboot.domain.impl;

import com.neusoft.elmboot.domain.ConsumeCreditRule;

public class TransferMoneyCreditRule extends ConsumeCreditRule {
    public TransferMoneyCreditRule(double formula) {
        super(formula);
    }

    @Override
    public Double getRule() {
        return this.getFormula();
    }
}

package com.tju.elmcloud.domain.impl;

import com.tju.elmcloud.domain.ConsumeCreditRule;

public class TransferMoneyCreditRule extends ConsumeCreditRule {
    public TransferMoneyCreditRule(double formula) {
        super(formula);
    }

    @Override
    public Double getRule() {
        return this.getFormula();
    }
}

package com.neusoft.elmboot.po;

public class ConsumeCredit {
    private int creditNum;
    private double deductionMoney;

    public ConsumeCredit() {

    }

    public int getCreditNum() {
        return creditNum;
    }

    public double getDeductionMoney() {
        return deductionMoney;
    }

    public void setDeductionMoney(double deductionMoney) {
        this.deductionMoney = deductionMoney;
    }

    public void setCreditNum(int creditNum) {
        this.creditNum = creditNum;
    }
}

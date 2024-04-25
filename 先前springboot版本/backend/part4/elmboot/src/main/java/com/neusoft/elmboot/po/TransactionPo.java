package com.neusoft.elmboot.po;

public class TransactionPo {
    private Integer transactionId;
    private String time;
    private double money;
    private Integer type;
    private Integer inputwalletId;
    private Integer outputwalletId;

    public TransactionPo(String time,double money,Integer type,Integer inputwalletId,Integer outputwalletId){
        this.time=time;
        this.money=money;
        this.type=type;
        this.outputwalletId=outputwalletId;
        this.inputwalletId=inputwalletId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public String getTime() {
        return time;
    }

    public double getMoney() {
        return money;
    }

    public Integer getType() {
        return type;
    }

    public Integer getInputwalletId() {
        return inputwalletId;
    }

    public Integer getOutputwalletId() {
        return outputwalletId;
    }
}

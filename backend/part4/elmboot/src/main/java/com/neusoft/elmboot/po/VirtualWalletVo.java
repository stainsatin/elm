package com.neusoft.elmboot.po;

public class VirtualWalletVo {
    private double money;
    private Integer walletId;
    private Integer inputWalletId;
    private Integer outputWalletId;
    private Integer orderId;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getInputWalletId() {
        return inputWalletId;
    }

    public Integer getOutputWalletId() {
        return outputWalletId;
    }

    public void setOutputWalletId(Integer outputWalletId) {
        this.outputWalletId = outputWalletId;
    }

    public void setInputWalletId(Integer inputWalletId) {
        this.inputWalletId = inputWalletId;
    }

    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public Integer getWalletId() {
        return walletId;
    }
    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }
}

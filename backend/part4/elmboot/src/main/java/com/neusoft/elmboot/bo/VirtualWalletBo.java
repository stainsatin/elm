package com.neusoft.elmboot.bo;

public class VirtualWalletBo {
    private Integer walletId;
    private double balance;

    public VirtualWalletBo() {
        this.balance = 0.00;
    }

    public VirtualWalletBo(Integer walletId, double balance) {
        this.balance = balance;
        this.walletId = walletId;
    }

    public Integer getWalletId() {
        return walletId;
    }
    public double getBalance() {
        return balance;
    }
}

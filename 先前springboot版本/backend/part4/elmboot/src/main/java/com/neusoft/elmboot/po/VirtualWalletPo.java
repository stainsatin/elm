package com.neusoft.elmboot.po;

public class VirtualWalletPo {
    private Integer walletId;
    private double balance;

    public VirtualWalletPo(){
        this.balance=0.00;
    }
    public VirtualWalletPo(Integer walletId,double balance){
        this.balance=balance;
        this.walletId=walletId;
    }
    public Integer getWalletId() {
        return walletId;
    }
    public double getBalance() {
        return balance;
    }
}

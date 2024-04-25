package com.neusoft.elmboot.domain.impl;

import com.neusoft.elmboot.domain.VirtualWallet;

public class VirtualWalletImpl implements VirtualWallet {
    private Integer id;
    private double balance;

    public VirtualWalletImpl(Integer id,double balance){
        this.balance=balance;
        this.id=id;
    }

    @Override
    public int decreaseBalance(double money) {
        if(this.balance>=money){
            this.balance=this.balance-money;
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public int increaseBalance(double money) {
        this.balance=this.balance+money;
        return 1;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
}

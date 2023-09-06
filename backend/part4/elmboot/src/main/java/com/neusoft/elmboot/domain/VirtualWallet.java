package com.neusoft.elmboot.domain;

public interface VirtualWallet {
    public int decreaseBalance(double money);
    public int increaseBalance(double money);
    public double getBalance();
}

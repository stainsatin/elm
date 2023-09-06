package com.neusoft.elmboot.service;

public interface VirtualWalletService {
    public int transferMoney(Integer inputWalletId, Integer outputWalletId, double money,Integer orderId);
    public int withdrawMoney(Integer walletId,double money);
    public int recharge(Integer walletId,double money);
    public int userCreateVirtualWallet(String userId);
    public double queryBalance(Integer walletId);
    public int transferMoneyWithCredit(Integer inputWalletId, Integer outputWalletId, double money1,double money2,Integer orderId);
}

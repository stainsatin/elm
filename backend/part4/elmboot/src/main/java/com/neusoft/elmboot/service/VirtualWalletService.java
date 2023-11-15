package com.neusoft.elmboot.service;

import com.neusoft.elmboot.exception.wallet.CreateWalletFailedException;
import com.neusoft.elmboot.exception.wallet.UserHasCreatedWalletException;

public interface VirtualWalletService {
    public int transferMoney(Integer inputWalletId, Integer outputWalletId, double money, Integer orderId);

    public int withdrawMoney(Integer walletId, double money);

    public int recharge(Integer walletId, double money);

    public int userCreateVirtualWallet(String userId) throws UserHasCreatedWalletException, CreateWalletFailedException;

    public double queryBalance(Integer walletId);

    public int transferMoneyWithCredit(Integer inputWalletId, Integer outputWalletId, double money1, double money2, Integer orderId);
}

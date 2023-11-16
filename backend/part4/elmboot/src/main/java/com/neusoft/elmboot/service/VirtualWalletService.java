package com.neusoft.elmboot.service;

import com.neusoft.elmboot.exception.wallet.CreateWalletFailedException;
import com.neusoft.elmboot.exception.wallet.UserHasCreatedWalletException;
import com.neusoft.elmboot.exception.wallet.UserHasNotCreatedWalletIdException;

public interface VirtualWalletService {
    public int transferMoney(Integer inputWalletId, Integer outputWalletId, double money, Integer orderId);

    public int withdrawMoney(Integer walletId, double money);

    public int recharge(double money) throws UserHasNotCreatedWalletIdException;

    public int userCreateVirtualWallet() throws UserHasCreatedWalletException, CreateWalletFailedException;

    public double queryBalance() throws UserHasNotCreatedWalletIdException;

    public int transferMoneyWithCredit(Integer inputWalletId, Integer outputWalletId, double money1, double money2, Integer orderId);
}

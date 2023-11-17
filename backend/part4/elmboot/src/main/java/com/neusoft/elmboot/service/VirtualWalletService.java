package com.neusoft.elmboot.service;

import com.neusoft.elmboot.exception.wallet.*;

public interface VirtualWalletService {
    public int transferMoney(String targetUsername, Integer targetWalletId, double money) throws UsernameWalletIdNotMatchException, UserHasNotCreatedWalletIdException, BalanceRemainNotEnoughException, TransferFailedException;

    public int recharge(double money) throws UserHasNotCreatedWalletIdException;

    public int userCreateVirtualWallet() throws UserHasCreatedWalletException, CreateWalletFailedException;

    public double queryBalance() throws UserHasNotCreatedWalletIdException;
}

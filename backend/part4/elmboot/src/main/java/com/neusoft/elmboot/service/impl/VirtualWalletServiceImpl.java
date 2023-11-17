package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.bo.TransactionBo;
import com.neusoft.elmboot.bo.VirtualWalletBo;
import com.neusoft.elmboot.domain.VirtualWallet;
import com.neusoft.elmboot.domain.impl.VirtualWalletImpl;
import com.neusoft.elmboot.entity.User;
import com.neusoft.elmboot.exception.wallet.*;
import com.neusoft.elmboot.mapper.OrdersMapper;
import com.neusoft.elmboot.mapper.TransactionMapper;
import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.mapper.VirtualWalletMapper;
import com.neusoft.elmboot.service.VirtualWalletService;
import com.neusoft.elmboot.util.CommonUtil;
import com.neusoft.elmboot.util.UserUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VirtualWalletServiceImpl implements VirtualWalletService {
    @Resource
    private VirtualWalletMapper virtualWalletMapper;
    @Resource
    private TransactionMapper transactionMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public int transferMoney(String targetUsername, Integer targetWalletId, double money) throws UserHasNotCreatedWalletIdException, UsernameWalletIdNotMatchException, BalanceRemainNotEnoughException, TransferFailedException {

        String userId = UserUtil.getUserId();
        Integer outputWalletId = userMapper.getWalletIdByUserId(userId);
        if (outputWalletId == null)
            throw new UserHasNotCreatedWalletIdException();
        User targetUser = userMapper.getUserByUsernameWalletId(targetUsername, targetWalletId);
        if (targetUser == null)
            throw new UsernameWalletIdNotMatchException();
        Integer inputWalletId = targetUser.getWalletId();
        double inputBalance = virtualWalletMapper.queryBalance(inputWalletId);
        double outputBalance = virtualWalletMapper.queryBalance(outputWalletId);
        VirtualWallet inputVirtualWallet = new VirtualWalletImpl(inputWalletId, inputBalance);
        VirtualWallet outputVirtualWallet = new VirtualWalletImpl(outputWalletId, outputBalance);
        if (outputVirtualWallet.decreaseBalance(money) == 1 && inputVirtualWallet.increaseBalance(money) == 1) {
            VirtualWalletBo inputVirtualWalletBo = new VirtualWalletBo(inputWalletId, inputVirtualWallet.getBalance());
            VirtualWalletBo outputVirtualWalletBo = new VirtualWalletBo(outputWalletId, outputVirtualWallet.getBalance());
            TransactionBo transactionPo = new TransactionBo(CommonUtil.getCurrentDate(), money, 2, inputWalletId, outputWalletId);
            int done1 = virtualWalletMapper.updateBalance(inputVirtualWalletBo);
            int done2 = virtualWalletMapper.updateBalance(outputVirtualWalletBo);
            int done3 = transactionMapper.writeTransaction(transactionPo);
            if (done1 == 1 && done2 == 1 && done3 == 1)
                return 1;
            else throw new TransferFailedException();
        } else {
            if (outputVirtualWallet.decreaseBalance(money) == 0) throw new BalanceRemainNotEnoughException();
            else throw new TransferFailedException();
        }
    }

    @Override
    @Transactional
    public int recharge(double money) throws UserHasNotCreatedWalletIdException {
        Integer walletId = userMapper.getWalletIdByUserId(UserUtil.getUserId());
        if (walletId == null) {
            throw new UserHasNotCreatedWalletIdException();
        }
        double balance = virtualWalletMapper.queryBalance(walletId);
        VirtualWallet virtualWallet = new VirtualWalletImpl(walletId, balance);
        if (virtualWallet.increaseBalance(money) == 1) {
            VirtualWalletBo virtualWalletBo = new VirtualWalletBo(walletId, virtualWallet.getBalance());
            TransactionBo transactionPo = new TransactionBo(CommonUtil.getCurrentDate(), money, 0, walletId, null);
            int done1 = virtualWalletMapper.updateBalance(virtualWalletBo);
            int done2 = transactionMapper.writeTransaction(transactionPo);
            if (done2 == 1 && done1 == 1)
                return transactionPo.getTransactionId();
            else
                return 0;
        } else {
            return 0;
        }
    }

    @Override
    @Transactional
    public int userCreateVirtualWallet() throws UserHasCreatedWalletException, CreateWalletFailedException {

        String userId = UserUtil.getUserId();
        Integer walletId = userMapper.getWalletIdByUserId(userId);
        if (walletId != null) {
            throw new UserHasCreatedWalletException();
        }

        VirtualWalletBo virtualWalletBo = new VirtualWalletBo();
        int done = virtualWalletMapper.createVirtualWallet(virtualWalletBo);
        walletId = virtualWalletBo.getWalletId();

        int finish = userMapper.updateWalletId(userId, walletId);

        if (finish == 1 && done == 1)
            return walletId;
        else throw new CreateWalletFailedException();
    }

    @Override
    public double queryBalance() throws UserHasNotCreatedWalletIdException {
        String userId = UserUtil.getUserId();
        Integer walletId = userMapper.getWalletIdByUserId(userId);
        if (walletId == null)
            throw new UserHasNotCreatedWalletIdException();
        return virtualWalletMapper.queryBalance(walletId);
    }
}

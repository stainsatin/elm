package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.domain.VirtualWallet;
import com.neusoft.elmboot.domain.impl.VirtualWalletImpl;
import com.neusoft.elmboot.mapper.OrdersMapper;
import com.neusoft.elmboot.mapper.TransactionMapper;
import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.mapper.VirtualWalletMapper;
import com.neusoft.elmboot.po.TransactionPo;
import com.neusoft.elmboot.po.VirtualWalletPo;
import com.neusoft.elmboot.service.VirtualWalletService;
import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VirtualWalletServiceImpl implements VirtualWalletService {
    @Autowired
    private VirtualWalletMapper virtualWalletMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
    public int transferMoney(Integer inputWalletId, Integer outputWalletId, double money,Integer orderId) {
        double inputBalance=virtualWalletMapper.queryBalance(inputWalletId);
        double outputBalance=virtualWalletMapper.queryBalance(outputWalletId);
        VirtualWallet inputVirtualWallet=new VirtualWalletImpl(inputWalletId,inputBalance);
        VirtualWallet outputVirtualWallet=new VirtualWalletImpl(outputWalletId,outputBalance);
        if(outputVirtualWallet.decreaseBalance(money)==1&&inputVirtualWallet.increaseBalance(money)==1){
                VirtualWalletPo inputVirtualWalletPo=new VirtualWalletPo(inputWalletId,inputVirtualWallet.getBalance());
                VirtualWalletPo outputVirtualWalletPo=new VirtualWalletPo(outputWalletId,outputVirtualWallet.getBalance());
                TransactionPo transactionPo=new TransactionPo(CommonUtil.getCurrentDate(),money,2,inputWalletId,outputWalletId);
                int done1=virtualWalletMapper.updateBalance(inputVirtualWalletPo);
                int done2=virtualWalletMapper.updateBalance(outputVirtualWalletPo);
                int done3=transactionMapper.writeTransaction(transactionPo);
                int done4=ordersMapper.payOrders(orderId);
                if(done1==1&&done2==1&&done3==1&&done4==1)
                    return 1;
                else
                    return 0;
        }
        else {
            return 0;
        }
    }


    @Override
    @Transactional
    public int withdrawMoney(Integer walletId, double money) {
        double balance=virtualWalletMapper.queryBalance(walletId);
        VirtualWallet virtualWallet=new VirtualWalletImpl(walletId,balance);
        if(virtualWallet.decreaseBalance(money)==1){
            VirtualWalletPo virtualWalletPo=new VirtualWalletPo(walletId,virtualWallet.getBalance());
            TransactionPo transactionPo=new TransactionPo(CommonUtil.getCurrentDate(),money,1,null,walletId);
            int done1=virtualWalletMapper.updateBalance(virtualWalletPo);
            int done2=transactionMapper.writeTransaction(transactionPo);
            if(done2==1&&done1==1)
                return 1;
            else
                return 0;
        }
        else {
            return 0;
        }
    }

    @Override
    @Transactional
    public int recharge(Integer walletId, double money) {
        double balance=virtualWalletMapper.queryBalance(walletId);
        VirtualWallet virtualWallet=new VirtualWalletImpl(walletId,balance);
        if(virtualWallet.increaseBalance(money)==1){
            VirtualWalletPo virtualWalletPo=new VirtualWalletPo(walletId,virtualWallet.getBalance());
            TransactionPo transactionPo=new TransactionPo(CommonUtil.getCurrentDate(),money,0,walletId,null);
            int done1=virtualWalletMapper.updateBalance(virtualWalletPo);
            int done2=transactionMapper.writeTransaction(transactionPo);
            if(done2==1&&done1==1)
                return transactionPo.getTransactionId();
            else
                return 0;
        }
        else {
            return 0;
        }
    }

    @Override
    @Transactional
    public int userCreateVirtualWallet(String userId) {
        VirtualWalletPo virtualWalletPo=new VirtualWalletPo();
        int done1=virtualWalletMapper.createVirtualWallet(virtualWalletPo);
        int done2=userMapper.updateWalletId(userId,virtualWalletPo.getWalletId());
        if(done2==1&&done1==1)
            return virtualWalletPo.getWalletId();
        else
            return -1;
    }

    @Override
    public double queryBalance(Integer walletId) {
        return virtualWalletMapper.queryBalance(walletId);
    }

    @Override
    public int transferMoneyWithCredit(Integer inputWalletId, Integer outputWalletId, double money1, double money2, Integer orderId) {
        double inputBalance=virtualWalletMapper.queryBalance(inputWalletId);
        double outputBalance=virtualWalletMapper.queryBalance(outputWalletId);
        VirtualWallet inputVirtualWallet=new VirtualWalletImpl(inputWalletId,inputBalance);
        VirtualWallet outputVirtualWallet=new VirtualWalletImpl(outputWalletId,outputBalance);
        if(outputVirtualWallet.decreaseBalance(money2)==1&&inputVirtualWallet.increaseBalance(money1)==1){
            VirtualWalletPo inputVirtualWalletPo=new VirtualWalletPo(inputWalletId,inputVirtualWallet.getBalance());
            VirtualWalletPo outputVirtualWalletPo=new VirtualWalletPo(outputWalletId,outputVirtualWallet.getBalance());
            TransactionPo transactionPo=new TransactionPo(CommonUtil.getCurrentDate(),money1,2,inputWalletId,outputWalletId);
            int done1=virtualWalletMapper.updateBalance(inputVirtualWalletPo);
            int done2=virtualWalletMapper.updateBalance(outputVirtualWalletPo);
            int done3=transactionMapper.writeTransaction(transactionPo);
            int done4=ordersMapper.payOrders(orderId);
            if(done1==1&&done2==1&&done3==1&&done4==1)
                return transactionPo.getTransactionId();
            else
                return 0;
        }
        else {
            return 0;
        }
    }
}

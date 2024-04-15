package com.tju.elmcloud.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.tju.elmcloud.mapper.OrdersMapper;
import com.tju.elmcloud.mapper.TransactionMapper;
import com.tju.elmcloud.mapper.UserMapper;
import com.tju.elmcloud.mapper.VirtualWalletMapper;
import com.tju.elmcloud.po.TransactionPo;
import com.tju.elmcloud.po.VirtualWalletPo;
import com.tju.elmcloud.service.VirtualWalletService;
import com.tju.elmcloud.util.CommonUtil;
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
        if(inputBalance<money) {
        	return 0;
        }
        BigDecimal inputNum = new BigDecimal(inputBalance);
        BigDecimal outputNum = new BigDecimal(outputBalance);
        BigDecimal change = new BigDecimal(money);
        inputNum = inputNum.subtract(change);
        outputNum = outputNum.add(change);
        inputNum = inputNum.setScale(2, RoundingMode.HALF_UP);
        outputNum = outputNum.setScale(2, RoundingMode.HALF_UP);
        
        VirtualWalletPo inputVirtualWalletPo=new VirtualWalletPo(inputWalletId,inputNum.doubleValue());
        VirtualWalletPo outputVirtualWalletPo=new VirtualWalletPo(outputWalletId,outputNum.doubleValue());
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


    @Override
    @Transactional
    public int withdrawMoney(Integer walletId, double money) {
        double balance=virtualWalletMapper.queryBalance(walletId);
        if(balance<money) {
        	return 0;
        }
        BigDecimal inputNum = new BigDecimal(balance);
        BigDecimal change = new BigDecimal(money);
        BigDecimal outputNum = inputNum.subtract(change);
        outputNum = outputNum.setScale(2, RoundingMode.HALF_UP);
        VirtualWalletPo virtualWalletPo=new VirtualWalletPo(walletId,outputNum.doubleValue());
        TransactionPo transactionPo=new TransactionPo(CommonUtil.getCurrentDate(),money,1,null,walletId);
        int done1=virtualWalletMapper.updateBalance(virtualWalletPo);
        int done2=transactionMapper.writeTransaction(transactionPo);
        if(done2==1&&done1==1)
            return 1;
        else
            return 0;
            
}

    @Override
    @Transactional
    public int recharge(Integer walletId, double money) {
        double balance=virtualWalletMapper.queryBalance(walletId);
        BigDecimal inputNum = new BigDecimal(balance);
        BigDecimal change = new BigDecimal(money);
        inputNum = inputNum.add(change);
        inputNum = inputNum.setScale(2, RoundingMode.HALF_UP);
        VirtualWalletPo virtualWalletPo=new VirtualWalletPo(walletId,inputNum.doubleValue());
        TransactionPo transactionPo=new TransactionPo(CommonUtil.getCurrentDate(),money,0,walletId,null);
        int done1=virtualWalletMapper.updateBalance(virtualWalletPo);
        int done2=transactionMapper.writeTransaction(transactionPo);
        if(done2==1&&done1==1)
            return transactionPo.getTransactionId();
        else
            return 0;
    }

    @Override
    @Transactional
    public int userCreateVirtualWallet(String userId) {
        VirtualWalletPo virtualWalletPo=new VirtualWalletPo();
        int done1=virtualWalletMapper.createVirtualWallet(virtualWalletPo);
        int done2=virtualWalletMapper.updateWalletId(userId,123);
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
        if(inputBalance<money2) {
        	return 0;
        }
        BigDecimal inputNum = new BigDecimal(inputBalance);
        BigDecimal outputNum = new BigDecimal(outputBalance);
        BigDecimal change1 = new BigDecimal(money1);
        BigDecimal change2 = new BigDecimal(money2);
        inputNum = inputNum.subtract(change2);
        outputNum = outputNum.add(change1);
        inputNum = inputNum.setScale(2, RoundingMode.HALF_UP);
        outputNum = outputNum.setScale(2, RoundingMode.HALF_UP);
        VirtualWalletPo inputVirtualWalletPo=new VirtualWalletPo(inputWalletId,inputNum.doubleValue());
        VirtualWalletPo outputVirtualWalletPo=new VirtualWalletPo(outputWalletId,outputNum.doubleValue());
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
}

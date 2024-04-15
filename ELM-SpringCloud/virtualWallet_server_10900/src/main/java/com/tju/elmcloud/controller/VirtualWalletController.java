package com.tju.elmcloud.controller;

import com.tju.elmcloud.service.VirtualWalletService;
import com.tju.elmcloud.po.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/VirtualWalletController")
public class VirtualWalletController {

    @Autowired
    VirtualWalletService virtualWalletService;
    
    @RequestMapping("/withdrawMoney/{walletId}/{money}")
    public CommonResult<Integer> withdrawMoney(@PathVariable("walletId") Integer walletId,
            @PathVariable("money") double money) throws Exception{
        return new CommonResult(200,"success",
        		virtualWalletService.withdrawMoney(walletId,money));
    }
    @RequestMapping("/transferMoney/{inputWalletId}/{outputWalletId}/{money}/{orderId}")
    public CommonResult<Integer> transferMoney(@PathVariable("inputWalletId") Integer inputWalletId,
            @PathVariable("outputWalletId") Integer outputWalletId,
            @PathVariable("money") double money,
            @PathVariable("orderId") Integer orderId
            )throws Exception{
    	return new CommonResult(200,"success", 
    			virtualWalletService.transferMoney(inputWalletId,outputWalletId,money,orderId));
    }
    @RequestMapping("/recharge/{walletId}/{money}")
    public CommonResult<Integer> recharge(@PathVariable("walletId") Integer walletId,
            @PathVariable("money") double money)throws Exception{
    	return new CommonResult(200,"success",  virtualWalletService.recharge(walletId,money));
    }

    @RequestMapping("/userCreateVirtualWallet/{userId}")
    public CommonResult<Integer> userCreateVirtualWallet(@PathVariable("userId") String userId)throws Exception{
    	return new CommonResult(200,"success",  virtualWalletService.userCreateVirtualWallet(userId));
    }

    @RequestMapping("/queryBalance/{walletId}")
    public CommonResult<Double> queryBalance(@PathVariable("walletId") Integer walletId) throws Exception{
    	return new CommonResult(200,"success", virtualWalletService.queryBalance(walletId));
    }
    
    @RequestMapping("/transferMoneyWithCredit/{inputWalletId}/{outputWalletId}/{money1}/{money2}/{orderId}")
    public CommonResult<Integer> transferMoneyWithCredit(@PathVariable("inputWalletId") Integer inputWalletId,
            @PathVariable("outputWalletId") Integer outputWalletId,
            @PathVariable("money1") double money1,
            @PathVariable("money2") double money2,
            @PathVariable("orderId") Integer orderId
            ) throws Exception{
    	return new CommonResult(200,"success", virtualWalletService.transferMoneyWithCredit(inputWalletId, outputWalletId, money1, money2, orderId));
    }
}
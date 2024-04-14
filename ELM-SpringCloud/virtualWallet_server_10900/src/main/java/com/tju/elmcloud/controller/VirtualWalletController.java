package com.tju.elmcloud.controller;

import com.tju.elmcloud.service.VirtualWalletService;
import com.tju.elmcloud.po.CommonResult;
import com.tju.elmcloud.po.VirtualWalletVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/VirtualWalletController")
public class VirtualWalletController {
    @Autowired
    VirtualWalletService virtualWalletService;
    @RequestMapping("/withdrawMoney")
    public CommonResult<Integer> withdrawMoney(VirtualWalletVo virtualWalletVo) throws Exception{
        return new CommonResult(200,"success",
        		virtualWalletService.withdrawMoney(virtualWalletVo.getWalletId(),virtualWalletVo.getMoney()));
    }
    @RequestMapping("/transferMoney")
    public CommonResult<Integer> transferMoney(VirtualWalletVo virtualWalletVo)throws Exception{
    	return new CommonResult(200,"success", 
    			virtualWalletService.transferMoney(virtualWalletVo.getInputWalletId(),virtualWalletVo.getOutputWalletId(),virtualWalletVo.getMoney(),virtualWalletVo.getOrderId()));
    }
    @RequestMapping("/recharge")
    public CommonResult<Integer> recharge(VirtualWalletVo virtualWalletVo)throws Exception{
    	return new CommonResult(200,"success",  virtualWalletService.recharge(virtualWalletVo.getWalletId(),virtualWalletVo.getMoney()));
    }

    @RequestMapping("/userCreateVirtualWallet")
    public CommonResult<Integer> userCreateVirtualWallet(VirtualWalletVo virtualWalletVo)throws Exception{
    	return new CommonResult(200,"success",  virtualWalletService.userCreateVirtualWallet(virtualWalletVo.getUserId()));
    }

    @RequestMapping("/queryBalance")
    public CommonResult<Double> queryBalance(VirtualWalletVo virtualWalletVo) throws Exception{
    	return new CommonResult(200,"success", virtualWalletService.queryBalance(virtualWalletVo.getWalletId()));
    }
}
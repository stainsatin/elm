package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.service.VirtualWalletService;
import com.neusoft.elmboot.po.VirtualWalletVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/VirtualWalletController")
public class VirtualWalletController {
    @Autowired
    VirtualWalletService virtualWalletService;
    @RequestMapping("/withdrawMoney")
    public int withdrawMoney(VirtualWalletVo virtualWalletVo) throws Exception{
        return virtualWalletService.withdrawMoney(virtualWalletVo.getWalletId(),virtualWalletVo.getMoney());
    }
    @RequestMapping("/transferMoney")
    public int transferMoney(VirtualWalletVo virtualWalletVo)throws Exception{
        return virtualWalletService.transferMoney(virtualWalletVo.getInputWalletId(),virtualWalletVo.getOutputWalletId(),virtualWalletVo.getMoney(),virtualWalletVo.getOrderId());
    }
    @RequestMapping("/recharge")
    public int recharge(VirtualWalletVo virtualWalletVo)throws Exception{
        return virtualWalletService.recharge(virtualWalletVo.getWalletId(),virtualWalletVo.getMoney());
    }

    @RequestMapping("/userCreateVirtualWallet")
    public int userCreateVirtualWallet(VirtualWalletVo virtualWalletVo)throws Exception{
        return virtualWalletService.userCreateVirtualWallet(virtualWalletVo.getUserId());
    }

    @RequestMapping("/queryBalance")
    public double queryBalance(VirtualWalletVo virtualWalletVo) throws Exception{
        return virtualWalletService.queryBalance(virtualWalletVo.getWalletId());
    }
}

package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.dto.Result;
import com.neusoft.elmboot.exception.wallet.CreateWalletFailedException;
import com.neusoft.elmboot.exception.wallet.UserHasCreatedWalletException;
import com.neusoft.elmboot.exception.wallet.UserHasNotCreatedWalletIdException;
import com.neusoft.elmboot.po.VirtualWalletVo;
import com.neusoft.elmboot.service.VirtualWalletService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/virtual-wallet")
public class VirtualWalletController {
    @Resource
    VirtualWalletService virtualWalletService;

    @RequestMapping("/withdrawMoney")
    public int withdrawMoney(VirtualWalletVo virtualWalletVo) throws Exception {
        return virtualWalletService.withdrawMoney(virtualWalletVo.getWalletId(), virtualWalletVo.getMoney());
    }

    @RequestMapping("/transferMoney")
    public int transferMoney(VirtualWalletVo virtualWalletVo) throws Exception {
        return virtualWalletService.transferMoney(virtualWalletVo.getInputWalletId(), virtualWalletVo.getOutputWalletId(), virtualWalletVo.getMoney(), virtualWalletVo.getOrderId());
    }

    @PostMapping
    public Result recharge(double money) throws UserHasNotCreatedWalletIdException {
        return Result.success(virtualWalletService.recharge(money));
    }

    @PostMapping("/create")
    public Result userCreateVirtualWallet() throws CreateWalletFailedException, UserHasCreatedWalletException {
        return Result.success(virtualWalletService.userCreateVirtualWallet());
    }

    @GetMapping
    public Result queryBalance() throws UserHasNotCreatedWalletIdException {
        return Result.success(virtualWalletService.queryBalance());
    }
}

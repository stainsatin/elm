package com.tju.elmcloud.feign;

import com.tju.elmcloud.po.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name = "virtualWallet-server", fallback = WalletFeignClientCallBack.class)
public interface WalletFeignClient {

    @PutMapping("/VirtualWalletController/recharge/{walletId}/{money}")
    public CommonResult recharge(@PathVariable("money") double money,
                                 @PathVariable("walletId") Integer walletId);

    @PutMapping("/VirtualWalletController/transferMoneyWithCredit/{inputWalletId}/{outputWalletId}/{money1}/{money2}/{orderId}")
    public CommonResult transferMoneyWithCredit(@PathVariable("inputWalletId") Integer inputWalletId,
                                                @PathVariable("outputWalletId") Integer outputWalletId,
                                                @PathVariable("money1") double money1,
                                                @PathVariable("money2") double money2,
                                                @PathVariable("orderId") Integer orderId);
}

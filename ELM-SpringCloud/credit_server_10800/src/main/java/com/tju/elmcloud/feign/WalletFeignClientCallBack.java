package com.tju.elmcloud.feign;

import com.tju.elmcloud.po.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class WalletFeignClientCallBack implements WalletFeignClient{
    @Override
    public CommonResult recharge(double money, Integer walletId) {
        return new CommonResult(403, "feign触发了熔断降级", null);
    }

    @Override
    public CommonResult transferMoneyWithCredit(Integer inputWalletId, Integer outputWalletId, double money1, double money2, Integer orderId) {
        return new CommonResult(403, "feign触发了熔断降级", null);
    }
}

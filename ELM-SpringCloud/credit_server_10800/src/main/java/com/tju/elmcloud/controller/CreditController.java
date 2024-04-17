package com.tju.elmcloud.controller;

import com.tju.elmcloud.feign.WalletFeignClient;
import com.tju.elmcloud.po.CommonResult;
import com.tju.elmcloud.po.CreditRecord;
import com.tju.elmcloud.po.CreditRulePo;
import com.tju.elmcloud.service.CreditService;
import com.tju.elmcloud.vo.ConsumeCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/CreditController")
public class CreditController {

    /*@Autowired
    private RestTemplate restTemplate;*/

    @Autowired
    CreditService creditService;

    /*@Autowired
    private DiscoveryClient discoveryClient;*/

    @Resource
    private WalletFeignClient walletFeignClient;
    //此处用于测试Bus动态刷新
    @Value("${eureka.instance.lease-renewal-interval-in-seconds}")
    private int renewal;
    @Value("${eureka.instance.lease-expiration-duration-in-seconds}")
    private int expiration;

    @GetMapping("/queryEarningCreditBySign/{userId}")
    public CommonResult<Integer> queryEarningCreditBySign(@PathVariable("userId") String userId) {
        return new CommonResult<Integer>(200, "success，renewal：" + renewal + "，expiration：" + expiration, creditService.queryEarningCreditBySign(userId));
    }

    @PutMapping("/earnCreditBySign/{userId}/{creditNum}")
    public CommonResult<Integer> earnCreditBySign(@PathVariable("userId") String userId, @PathVariable("creditNum") int creditNum) {
        System.out.println(userId);
        System.out.println(creditNum);
        return new CommonResult<Integer>(200, "success，renewal：" + renewal + "，expiration：" + expiration, creditService.earnCreditBySign(userId, creditNum));
    }

    @GetMapping("/queryEarnCreditByRecharge/{userId}/{money}")
    public CommonResult<Integer> queryEarnCreditByRecharge(@PathVariable("userId") String userId, @PathVariable("money") Integer money) {
        return new CommonResult<Integer>(200, "success，renewal：" + renewal + "，expiration：" + expiration, creditService.queryEarnCreditByRecharge(userId, money));
    }

    @Transactional
    @PutMapping("/earnCreditBySignAndRecharge/{userId}/{walletId}/{money}/{creditNum}")
    public CommonResult<Integer> earnCreditBySignAndRecharge(
            @PathVariable("userId") String userId,
            @PathVariable("walletId") Integer walletId,
            @PathVariable("money") double money,
            @PathVariable("creditNum") Integer creditNum
            ) {
        CommonResult<Integer> result = walletFeignClient.recharge(money, walletId);
        Integer response = 0;
        if (result.getCode() == 200) {
            Integer transactionId = result.getResult();
            if (transactionId == 0) response = 0;
            else response = creditService.earnCreditBySign(userId, creditNum, transactionId);
        } else {
            response = 0;
        }
        return new CommonResult<Integer>(200, "success，renewal：" + renewal + "，expiration：" + expiration, response);
    }

    @GetMapping("/queryAvailableCredit/{userId}")
    public CommonResult<Integer> queryAvailableCredit(@PathVariable("userId") String userId) {
        return new CommonResult<Integer>(200, "success，renewal：" + renewal + "，expiration：" + expiration, creditService.queryAvailableCredit(userId));
    }

    @GetMapping("/consumeCreditByPaying/{userId}/{money}/{creditNum}")
    public CommonResult<ConsumeCredit> consumeCreditByPaying(@PathVariable("userId") String userId, @PathVariable("money") Integer money, @PathVariable("creditNum") Integer creditNum) {
        return new CommonResult<ConsumeCredit>(200, "success，renewal：" + renewal + "，expiration：" + expiration, creditService.consumeCreditByPaying(userId, money, creditNum));
    }

    @PostMapping("/transferMoneyWithCreditConsume/{inputWalletId}/{outputWalletId}/{money}/{orderId}/{deductionMoney}/{creditNum}/{userId}")
    public CommonResult<Integer> transferMoneyWithCreditConsume(@PathVariable("inputWalletId") int inputWalletId, @PathVariable("outputWalletId") int outputWalletId, @PathVariable("money") double money, @PathVariable("orderId") int orderId, @PathVariable("deductionMoney") double deductionMoney, @PathVariable("creditNum") int creditNum, @PathVariable("userId") String userId) {
               CommonResult<Integer> result = walletFeignClient.transferMoneyWithCredit(inputWalletId, outputWalletId, money, money - deductionMoney, orderId);
        System.out.println(result.getMessage());
        Integer response = 0;
        if (result.getCode() == 200) {
            Integer transactionId = result.getResult();
            if (transactionId == 0) response = 0;
            else response = creditService.transferMoneyWithCreditConsume(creditNum, transactionId, userId);
        } else {
            response = 0;
        }
        return new CommonResult<Integer>(200, "success，renewal：" + renewal + "，expiration：" + expiration, response);
    }

    @GetMapping("/queryAllCredit/{userId}")
    public CommonResult<List<CreditRecord>> queryAllCredit(@PathVariable("userId") String userId) {
        return new CommonResult<List<CreditRecord>>(200, "success，renewal：" + renewal + "，expiration：" + expiration, creditService.queryAllCredit(userId));
    }

    @PutMapping("/updateCreditRule")
    public CommonResult<Integer> updateCreditRule(@RequestParam(name = "id") Integer id, @RequestParam(name = "ruleCode") String ruleCode, @RequestParam(name = "type") Integer type, @RequestParam(name = "priority") Integer priority, @RequestParam(name = "credit") Integer credit, @RequestParam(name = "formula") Double formula, @RequestParam(name = "dailyCap") Integer dailyCap, @RequestParam(name = "totCap") Integer totCap, @RequestParam(name = "startTime") String startTime, @RequestParam(name = "endTime") String endTime, @RequestParam(name = "lifespan") Integer lifespan, @RequestParam(name = "state") Integer state) {
        CreditRulePo creditRulePo = new CreditRulePo(id, ruleCode, type, priority, credit, formula, dailyCap, totCap, startTime, endTime, lifespan, state);
        return new CommonResult<Integer>(200, "success，renewal：" + renewal + "，expiration：" + expiration, creditService.updateCreditRule(creditRulePo));
        // return creditService.updateCreditRule(creditRulePo);
    }

    @GetMapping("/queryAllCreditRule")
    public CommonResult<List<CreditRulePo>> queryAllCreditRule() {
        return new CommonResult<List<CreditRulePo>>(200, "success，renewal：" + renewal + "，expiration：" + expiration, creditService.queryAllCreditRule());
    }
}

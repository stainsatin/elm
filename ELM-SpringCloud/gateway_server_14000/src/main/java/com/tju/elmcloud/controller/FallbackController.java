package com.tju.elmcloud.controller;

import com.tju.elmcloud.po.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public CommonResult fallback() {
        return new CommonResult(403, "服务器繁忙，请稍后重试", null);
    }
}

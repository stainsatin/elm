package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.dto.Result;
import com.neusoft.elmboot.dto.SimpleUser;
import com.neusoft.elmboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private UserService userService;
    @PostMapping("/login")
    public Result login(SimpleUser simpleUser){
        try {
            return Result.success(userService.login(simpleUser.getUsername(),simpleUser.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            return Result.failure(40000,"用户名或密码错误");
        }
    }
}

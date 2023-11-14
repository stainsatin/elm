package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.dto.RegisterUserInfo;
import com.neusoft.elmboot.dto.Result;
import com.neusoft.elmboot.dto.SimpleUser;
import com.neusoft.elmboot.exception.UsernamePasswordNotMatchException;
import com.neusoft.elmboot.exception.UsernameUserIdRepeatedException;
import com.neusoft.elmboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(SimpleUser simpleUser) throws UsernamePasswordNotMatchException {
        return Result.success(userService.login(simpleUser.getUsername(), simpleUser.getPassword()));
    }

    @PostMapping("/register")
    public Result register(RegisterUserInfo user) throws UsernameUserIdRepeatedException {
        return Result.success(userService.register(user));
    }


}

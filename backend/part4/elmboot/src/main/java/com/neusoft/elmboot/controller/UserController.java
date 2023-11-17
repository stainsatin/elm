package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.bo.RegisterUserInfo;
import com.neusoft.elmboot.bo.Result;
import com.neusoft.elmboot.exception.user.UserIdNotFoundException;
import com.neusoft.elmboot.exception.user.UsernameUserIdRepeatedException;
import com.neusoft.elmboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

    @PutMapping
	public Result updateUserInfo(RegisterUserInfo user) throws UsernameUserIdRepeatedException, UserIdNotFoundException {
		return Result.success(userService.updateUserInfo(user));
	}

}

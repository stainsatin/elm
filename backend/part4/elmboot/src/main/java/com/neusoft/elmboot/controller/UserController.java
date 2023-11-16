package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.dto.RegisterUserInfo;
import com.neusoft.elmboot.dto.Result;
import com.neusoft.elmboot.exception.user.UserIdNotFoundException;
import com.neusoft.elmboot.exception.user.UsernameUserIdRepeatedException;
import com.neusoft.elmboot.service.UserService;
import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

    @RequestMapping("/updateUserPassword")
    public int updateUserPassword(String userId,String oldPass,String newPass) throws NoSuchAlgorithmException {
		oldPass = CommonUtil.encodePassword(oldPass);
		newPass = CommonUtil.encodePassword(newPass);
    	return userService.updateUserPassword(userId, oldPass, newPass);
    }

    @PutMapping
	public Result updateUserInfo(RegisterUserInfo user) throws UsernameUserIdRepeatedException, UserIdNotFoundException {
		return Result.success(userService.updateUserInfo(user));
	}

}

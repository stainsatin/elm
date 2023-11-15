package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.dto.RegisterUserInfo;
import com.neusoft.elmboot.dto.Result;
import com.neusoft.elmboot.exception.user.UserIdNotFoundException;
import com.neusoft.elmboot.exception.user.UsernameNotFoundException;
import com.neusoft.elmboot.exception.user.UsernameUserIdRepeatedException;
import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.neusoft.elmboot.entity.User;
import com.neusoft.elmboot.service.UserService;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUserByIdByPass")
	public User getUserByIdByPass( String userId, String password) throws NoSuchAlgorithmException {
		password = CommonUtil.encodePassword(password);
		return userService.getUserByIdByPass(userId, password);
	}

	@RequestMapping("/getUserById")
    public int getUserById(String userId){
    	return userService.getUserById(userId);
    }

	@RequestMapping("/saveUser")
    public int saveUser(User user) throws NoSuchAlgorithmException {
		String password = user.getPassword();
		password = CommonUtil.encodePassword(password);
		user.setPassword(password);
    	return userService.saveUser(user);
    }

    @PutMapping("/user")
    public int updateUserMsg(String userId,String username){
    	return userService.updateUserMsg(userId, username);
    }

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

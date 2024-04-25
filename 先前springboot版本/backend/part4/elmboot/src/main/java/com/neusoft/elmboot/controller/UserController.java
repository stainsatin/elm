package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/UserController")
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
	@RequestMapping("/updateUserMsg")
    public int updateUserMsg(String userId,String userName){
    	return userService.updateUserMsg(userId, userName);
    }
	@RequestMapping("/updateUserPassword")
    public int updateUserPassword(String userId,String oldPass,String newPass) throws NoSuchAlgorithmException {
		oldPass = CommonUtil.encodePassword(oldPass);
		newPass = CommonUtil.encodePassword(newPass);
    	return userService.updateUserPassword(userId, oldPass, newPass);
    }
}

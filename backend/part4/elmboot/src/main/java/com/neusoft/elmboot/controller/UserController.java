package com.neusoft.elmboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@RestController
@RequestMapping("/UserController")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUserByIdByPass")
	public User getUserByIdByPass( String userId, String password)throws Exception{
		return userService.getUserByIdByPass(userId, password);
	}
	@RequestMapping("/getUserById")
    public int getUserById(String userId)throws Exception{
    	return userService.getUserById(userId);
    }
	@RequestMapping("/saveUser")
    public int saveUser(User user)throws Exception{
    	return userService.saveUser(user);
    }
	@RequestMapping("/updateUserMsg")
    public int updateUserMsg(String userId,String userName)throws Exception{
    	return userService.updateUserMsg(userId, userName);
    }
	@RequestMapping("/updateUserPassword")
    public int updateUserPassword(String userId,String oldPass,String newPass)throws Exception{
    	return userService.updateUserPassword(userId, oldPass, newPass);
    }
}

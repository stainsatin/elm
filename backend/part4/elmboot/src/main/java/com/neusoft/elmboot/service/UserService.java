package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.User;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    public User getUserByIdByPass(String userId,String password) throws NoSuchAlgorithmException;
    public int getUserById(String userId);
    public int saveUser(User user) throws NoSuchAlgorithmException;
    public int updateUserMsg(String userId,String userName);
    public int updateUserPassword(String userId,String oldPass,String newPass) throws NoSuchAlgorithmException;
}

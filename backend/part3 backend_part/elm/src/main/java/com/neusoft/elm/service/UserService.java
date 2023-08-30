package com.neusoft.elm.service;

import com.neusoft.elm.po.User;
public interface UserService {
    public User getUserByIdByPass(String userId,String password);
    public int getUserById(String userId);
    public int saveUser(User user);
    public int updateUserMsg(String userId,String userName);
    public int updateUserPassword(String userId,String oldPass,String newPass);
}

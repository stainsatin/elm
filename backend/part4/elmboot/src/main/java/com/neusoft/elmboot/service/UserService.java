package com.neusoft.elmboot.service;

import com.neusoft.elmboot.dto.RegisterUserInfo;
import com.neusoft.elmboot.exception.user.UserIdNotFoundException;
import com.neusoft.elmboot.exception.user.UsernameNotFoundException;
import com.neusoft.elmboot.exception.user.UsernamePasswordNotMatchException;
import com.neusoft.elmboot.exception.user.UsernameUserIdRepeatedException;
import com.neusoft.elmboot.entity.User;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    public User getUserByIdByPass(String userId, String password) throws NoSuchAlgorithmException;

    public int getUserById(String userId);

    public int saveUser(User user) throws NoSuchAlgorithmException;

    public int updateUserMsg(String userId, String username);

    public int updateUserPassword(String userId, String oldPass, String newPass) throws NoSuchAlgorithmException;

    String login(String username,String password) throws  UsernamePasswordNotMatchException;

    String register(RegisterUserInfo user) throws UsernameUserIdRepeatedException;

    String getUserIdByUsername(String username) throws UsernameNotFoundException;

    String updateUserInfo(RegisterUserInfo user) throws UsernameUserIdRepeatedException, UserIdNotFoundException ;

    User getUserByUserId(String userId) throws UserIdNotFoundException;

}

package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.bo.RegisterUserInfo;
import com.neusoft.elmboot.entity.User;
import com.neusoft.elmboot.exception.user.UserIdNotFoundException;
import com.neusoft.elmboot.exception.user.UsernameNotFoundException;
import com.neusoft.elmboot.exception.user.UsernamePasswordNotMatchException;
import com.neusoft.elmboot.exception.user.UsernameUserIdRepeatedException;
import com.neusoft.elmboot.jwt.JwtUtil;
import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.service.UserService;
import com.neusoft.elmboot.util.CommonUtil;
import com.neusoft.elmboot.util.UserUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public String login(String username, String password) throws UsernamePasswordNotMatchException {
        password = CommonUtil.encodePassword(password);
        User user = userMapper.getUserByUserNamePassword(username, password);
        if (user == null) {
            throw new UsernamePasswordNotMatchException();
        }
        return jwtUtil.generateToken(user);
    }

    @Override
    public String register(RegisterUserInfo user) throws UsernameUserIdRepeatedException {
        User newUser = new User(
                UUID.randomUUID().toString(),
                CommonUtil.encodePassword(user.getPassword()),
                user.getUsername(),
                user.getUserSex(),
                user.getUserImg(),
                null
        );
        int count = userMapper.countUserByUsername(user.getUsername());
        if (count > 0) {
            throw new UsernameUserIdRepeatedException();
        }
        try {
            userMapper.saveUser(newUser);
            return "success";
        } catch (DuplicateKeyException e) {
            throw new UsernameUserIdRepeatedException();
        }
    }

    @Override
    public String getUserIdByUsername(String username) throws UsernameNotFoundException {
        int count = userMapper.countUserByUsername(username);
        if (count != 1) {
            throw new UsernameNotFoundException();
        }
        String userId = userMapper.getUserIdByUsername(username);
        return userId;
    }


    @Value("${jwt.header}")
    private String headerName;

    @Override
    public String updateUserInfo(RegisterUserInfo user) throws UsernameUserIdRepeatedException, UserIdNotFoundException {
        String userId = UserUtil.getUserId();
        User userData = this.getUserByUserId(userId);
        String usernameOld = userData.getUsername();
        String usernameNew = user.getUsername();
        String usernamePut = usernameOld;
        if (!usernameNew.equals("") && !usernameOld.equals(usernameNew)) {
            int count = userMapper.countUserByUsername(usernameNew);
            if (count > 0) {
                throw new UsernameUserIdRepeatedException();
            }
            usernamePut = usernameNew;
        }
        String passwordPut = user.getPassword();
        if (passwordPut.equals("")) {
            passwordPut = userData.getPassword();
        }else{
            passwordPut = CommonUtil.encodePassword(passwordPut);
        }


        Integer userSexPut = user.getUserSex();
        if (userSexPut == null) {
            userSexPut = userData.getUserSex();
        }

        String userImgPut = user.getUserImg();
        if (userImgPut == null || userImgPut.equals("")) {
            userImgPut = userData.getUserImg();
        }
        try {
            userMapper.updateUserInfo(userId,usernamePut,passwordPut,userSexPut,userImgPut);
            return "success";
        } catch (DuplicateKeyException e) {
            throw new UsernameUserIdRepeatedException();
        }
    }

    @Override
    public User getUserByUserId(String userId) throws UserIdNotFoundException {
        try {
            User user = userMapper.getUserByUserId(userId);
            return user;
        } catch (Exception e) {
            throw new UserIdNotFoundException();
        }
    }


}

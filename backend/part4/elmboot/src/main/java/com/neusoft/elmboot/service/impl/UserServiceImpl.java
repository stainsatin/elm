package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.dto.RegisterUserInfo;
import com.neusoft.elmboot.exception.user.UserIdNotFoundException;
import com.neusoft.elmboot.exception.user.UsernameNotFoundException;
import com.neusoft.elmboot.exception.user.UsernamePasswordNotMatchException;
import com.neusoft.elmboot.exception.user.UsernameUserIdRepeatedException;
import com.neusoft.elmboot.jwt.JwtUtil;
import com.neusoft.elmboot.util.CommonUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.entity.User;
import com.neusoft.elmboot.service.UserService;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "use", key = "#userId + #password")
    @Override
    public User getUserByIdByPass(String userId, String password) {
        return userMapper.getUserByIdByPass(userId, password);
    }

    @Override
    public int getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userList", allEntries = true)},
            put = {@CachePut(cacheNames = "user", key = "#user.userId")})
    @Override
    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userList", allEntries = true)},
            put = {@CachePut(cacheNames = "user", key = "#userId")})
    @Override
    public int updateUserMsg(String userId, String username) {
        return userMapper.updateUserMsg(userId, username);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userList", allEntries = true)},
            put = {@CachePut(cacheNames = "user", key = "#userId")})
    @Override
    public int updateUserPassword(String userId, String oldPass, String newPass) {
        return userMapper.updateUserPassword(userId, oldPass, newPass);
    }

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
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("userId:" + userId);
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
        System.out.println("usernamePut:" + usernamePut);
        String passwordPut = user.getPassword();
        if (passwordPut.equals("")) {
            passwordPut = userData.getPassword();
        }else{
            passwordPut = CommonUtil.encodePassword(passwordPut);
        }

        System.out.println("passwordPut:" + passwordPut);

        Integer userSexPut = user.getUserSex();
        if (userSexPut == null) {
            userSexPut = userData.getUserSex();
        }
        System.out.println("userSexPut:" + userSexPut);

        String userImgPut = user.getUserImg();
        if (userImgPut == null || userImgPut.equals("")) {
            userImgPut = userData.getUserImg();
        }
        System.out.println("userImgPut:" + userImgPut);
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

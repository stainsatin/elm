package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.dto.RegisterUserInfo;
import com.neusoft.elmboot.exception.UsernamePasswordNotMatchException;
import com.neusoft.elmboot.exception.UsernameUserIdRepeatedException;
import com.neusoft.elmboot.jwt.JwtUtil;
import com.neusoft.elmboot.util.CommonUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.entity.User;
import com.neusoft.elmboot.service.UserService;

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
}

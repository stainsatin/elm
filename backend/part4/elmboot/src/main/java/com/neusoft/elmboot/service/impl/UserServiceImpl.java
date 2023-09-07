package com.neusoft.elmboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;


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
    public int updateUserMsg(String userId, String userName) {
        return userMapper.updateUserMsg(userId, userName);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "userList", allEntries = true)},
            put = {@CachePut(cacheNames = "user", key = "#userId")})
    @Override
    public int updateUserPassword(String userId, String oldPass, String newPass) {
        return userMapper.updateUserPassword(userId, oldPass, newPass);
    }
}

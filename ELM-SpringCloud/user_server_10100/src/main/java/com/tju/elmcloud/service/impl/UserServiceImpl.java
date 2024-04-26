package com.tju.elmcloud.service.impl;

import com.tju.elmcloud.constant.UserConstant;
import com.tju.elmcloud.manager.RedisManager;
import com.tju.elmcloud.mapper.UserMapper;
import com.tju.elmcloud.po.LoginVo;
import com.tju.elmcloud.po.User;
import com.tju.elmcloud.service.UserService;
import com.tju.elmcloud.utils.JWTProvider;
import com.tju.elmcloud.viewpo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private RedisManager redisManager;
    
    @Value("${jwt.prefix}")
    private String prefix;
    
    @Override
    public LoginVo login(String username,String password) {
    	User user = userMapper.getUserInfoByname(username);
    	if (user == null){
            return null;
        }

        // 判断用户名密码是否正确
        if (!password.equals(user.getPassword())){
            return null;
        }
        // 生成token
        String token = jwtProvider.generateToken(user.getUserName());

        // 将token存入redis
        redisManager.set(UserConstant.USER_TOKEN_KEY_REDIS + user.getUserName(),token,604800);
        LoginVo lg = new LoginVo();
        lg.setToken(prefix + " " +token);
        lg.setUserId(user.getUserId());
        lg.setUserName(username);
        return lg;
    }
    @Override
    public User getUserByIdByPass(User user) {
        return userMapper.getUserByIdByPass(user);
    }

    @Override
    public int getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public UserInfo getUserInfoById(String userId) {
        User user = userMapper.getUserInfoById(userId);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setUserName(user.getUserName());
        userInfo.setUserSex(user.getUserSex());
        return userInfo;
    }
}

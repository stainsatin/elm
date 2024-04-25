package com.tju.elmcloud.service;

import com.tju.elmcloud.po.LoginVo;
import com.tju.elmcloud.po.User;
import com.tju.elmcloud.viewpo.UserInfo;

public interface UserService {
	public LoginVo login(String username,String password);

    public User getUserByIdByPass(User user);

    public int getUserById(String userId);

    public int saveUser(User user);

    public int updateUserById(User user);

    public UserInfo getUserInfoById(String userId);
}

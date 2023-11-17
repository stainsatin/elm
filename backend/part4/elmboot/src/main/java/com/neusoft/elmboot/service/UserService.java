package com.neusoft.elmboot.service;

import com.neusoft.elmboot.bo.RegisterUserInfo;
import com.neusoft.elmboot.entity.User;
import com.neusoft.elmboot.exception.user.UserIdNotFoundException;
import com.neusoft.elmboot.exception.user.UsernameNotFoundException;
import com.neusoft.elmboot.exception.user.UsernamePasswordNotMatchException;
import com.neusoft.elmboot.exception.user.UsernameUserIdRepeatedException;

public interface UserService {

    String login(String username,String password) throws  UsernamePasswordNotMatchException;

    String register(RegisterUserInfo user) throws UsernameUserIdRepeatedException;

    String getUserIdByUsername(String username) throws UsernameNotFoundException;

    String updateUserInfo(RegisterUserInfo user) throws UsernameUserIdRepeatedException, UserIdNotFoundException ;

    User getUserByUserId(String userId) throws UserIdNotFoundException;

}

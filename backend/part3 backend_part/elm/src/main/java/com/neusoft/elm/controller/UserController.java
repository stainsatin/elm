package com.neusoft.elm.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.neusoft.elm.po.User;
import com.neusoft.elm.service.UserService;
import com.neusoft.elm.service.impl.UserServiceImpl;
public class UserController {
    public Object getUserByIdByPass(HttpServletRequest request) throws Exception{
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        UserService service = new UserServiceImpl();
        User user = service.getUserByIdByPass(userId, password);
        return user;
    }
    public Object getUserById(HttpServletRequest request) throws Exception{
        return null;
    }

    public Object saveUser(HttpServletRequest request) throws Exception{
    	return null;
    }
}

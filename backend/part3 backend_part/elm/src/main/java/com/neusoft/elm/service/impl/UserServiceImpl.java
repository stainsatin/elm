package com.neusoft.elm.service.impl;

import com.neusoft.elm.po.User;
import com.neusoft.elm.service.UserService;
import com.neusoft.elm.util.DBUtil;
import com.neusoft.elm.dao.UserDao;
import com.neusoft.elm.dao.impl.UserDaoImpl;
public class UserServiceImpl implements UserService {
    @Override
    public User getUserByIdByPass(String userId, String password) {
        User user = null;
        UserDao dao = new UserDaoImpl();
        try {
            DBUtil.getConnection();
            user = dao.getUserByIdByPass(userId, password);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return user;
    }

    @Override
    public int getUserById(String userId) {
        User user = new User();
        if(userId.equals("12345678912"))
        {
            user.setUserId("zyc");
        }
        else
        {
            user.setUserId("not zyc");
        }

        return 1;
    }

    @Override
    public int saveUser(User user) {
        return 0;
    }

}

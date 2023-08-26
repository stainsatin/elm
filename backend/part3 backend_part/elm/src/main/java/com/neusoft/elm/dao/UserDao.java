package com.neusoft.elm.dao;
import com.neusoft.elm.po.User;

public interface UserDao {
	public User getUserByIdByPass(String userId,String password)throws Exception;
}

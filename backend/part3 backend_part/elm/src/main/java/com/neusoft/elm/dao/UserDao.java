package com.neusoft.elm.dao;
import com.neusoft.elm.po.User;

public interface UserDao {
	public User getUserByIdByPass(String userId,String password)throws Exception;
	public int getUserById(String userId)throws Exception;
	public int saveUser(User user)throws Exception;
	public int updateUserMsg(String userId,String userName)throws Exception;
	public int updateUserPassword(String userId,String oldPass,String newPass)throws Exception;
}

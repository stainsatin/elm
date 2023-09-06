package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface UserMapper {
	@Select("select * from user where userId=#{userId} and password=#{password}")
	public User getUserByIdByPass(String userId,String password);

	@Select("select count(*) from user where userId=#{userId}")
	public int getUserById(String userId);
	
	@Insert("insert into user values(#{userId},#{password},#{userName},#{userSex},null,1)")
	public int saveUser(User user);
	
	@Update("update user set userName = #{userName} where userId = #{userId}")
	public int updateUserMsg(String userId,String userName);

	@Update("update user set password = #{newPass} where userId = #{userId} and password = #{oldPass}")
	public int updateUserPassword(String userId,String oldPass,String newPass);

	@Update("update user set walletId=#{walletId} where userId=#{userId}")
	public int updateWalletId(String userId,Integer walletId);

}

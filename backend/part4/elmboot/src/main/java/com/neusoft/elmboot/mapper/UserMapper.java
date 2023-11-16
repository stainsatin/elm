package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where userId=#{userId} and password=#{password}")
    public User getUserByIdByPass(String userId, String password);

    @Select("select count(*) from user where userId=#{userId}")
    public int getUserById(String userId);

    @Insert("insert into user values(#{userId},#{password},#{username},#{userSex},#{userImg},1,null)")
	public int saveUser(User user);

    @Update("update user set username = #{username} where userId = #{userId}")
    public int updateUserMsg(String userId, String username);

    @Update("update user set password = #{newPass} where userId = #{userId} and password = #{oldPass}")
    public int updateUserPassword(String userId, String oldPass, String newPass);

    @Update("update user set walletId=#{walletId} where userId=#{userId}")
    public int updateWalletId(String userId, Integer walletId);

    @Select("select * from user where username=#{username} and password=#{password}")
    User getUserByUserNamePassword(String username, String password);

    @Select("select count(*) from user where username=#{username}")
    int countUserByUsername(String username);

    @Select("select userId from user where username=#{username}")
    String getUserIdByUsername(String username);

    @Update("update user set username=#{username},password=#{password},userImg=#{userImg},userSex=#{userSex} where userId=#{userId}")
    void updateUserInfo(String userId, String username, String password, Integer userSex, String userImg);

    @Select("select * from user where userId=#{userId}")
    User getUserByUserId(String userId);

    @Select("select walletId from user where userId=#{userId}")
    Integer getWalletIdByUserId(String userId);

    @Select("select * from user where username=#{username} and walletId=#{walletId}")
    User getUserByUsernameWalletId(String username, Integer walletId);

}

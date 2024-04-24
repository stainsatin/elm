package com.tju.elmcloud.mapper;


import com.tju.elmcloud.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where userId=#{userId} and password = #{password}")
    public User getUserByIdByPass(User user);

    @Select("select count(*) from user where userId=#{userId}")
    public int getUserById(String userId);

    @Insert("insert into user values(#{userId},#{password},#{userName},#{userSex},null,1,null)")
    public int saveUser(User user);

    public int updateUserById(User user);

    @Select("select * from user where userId=#{userId}")
    public User getUserInfoById(String userId);
    
    @Select("select * from user where userName=#{userName}")
    public User getUserInfoByname(String userName);
    
    @Update("update user set walletId=#{walletId} where userId=#{userId}")
    public int updateWalletId(String userId, Integer walletId);
}

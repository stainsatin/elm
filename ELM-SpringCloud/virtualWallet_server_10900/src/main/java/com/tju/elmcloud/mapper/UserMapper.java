package com.tju.elmcloud.mapper;


import com.tju.elmcloud.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    
    @Update("update user set walletId=#{walletId} where userId=#{userId}")
    public int updateWalletId( @Param("userId") String userId, @Param("walletId") Integer walletId);
}

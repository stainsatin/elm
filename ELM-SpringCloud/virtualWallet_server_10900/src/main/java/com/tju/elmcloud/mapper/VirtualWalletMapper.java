package com.tju.elmcloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Insert;
import com.tju.elmcloud.po.VirtualWalletPo;

@Mapper
public interface VirtualWalletMapper {
    @Update("update virtualwallet set balance=#{balance} where walletId=#{walletId}")
    public int updateBalance(VirtualWalletPo virtualWalletPo);

    
    @Select("select balance from virtualwallet where walletId=#{walletId}")
    public double queryBalance(Integer walletId);
    
    @Select("select walletId from virtualwallet where userId=#{userId}")
    public int querywalletId(String userId);

    @Insert("insert into virtualwallet values(#{walletId},#{balance},#{userId})")
    public int createVirtualWallet(VirtualWalletPo virtualWalletPo);
    
    @Update("update user set walletId=#{walletId} where userId=#{userId}")
    public int updateWalletId(@Param("userId") String userId, @Param("walletId") Integer walletId);
}
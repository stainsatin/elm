package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.VirtualWalletPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface VirtualWalletMapper {
    @Update("update virtualwallet set balance=#{balance} where walletId=#{walletId}")
    public int updateBalance(VirtualWalletPo virtualWalletPo);

    @Select("select balance from virtualwallet where walletId=#{walletId}")
    public double queryBalance(Integer walletId);

    public int createVirtualWallet(VirtualWalletPo virtualWalletPo);
}

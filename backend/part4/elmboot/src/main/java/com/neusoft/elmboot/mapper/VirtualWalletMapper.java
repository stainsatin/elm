package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.VirtualWalletPo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface VirtualWalletMapper {
    @Update("update virtualwallet set balance=#{balance} where walletId=#{walletId}")
    public int updateBalance(VirtualWalletPo virtualWalletPo);

    @Select("select balance from virtualwallet where walletId=#{walletId}")
    public double queryBalance(Integer walletId);

    @Insert("insert into virtualwallet (balance) values(0.00)")
    @Options(useGeneratedKeys = true)
    @SelectKey(statement = "select last_insert_id()", keyProperty = "walletId", before = false, resultType = int.class)
    public int createVirtualWallet(VirtualWalletPo virtualWalletPo);

    @Select("select * from virtualwallet where walletId=#{walletId}")
    public VirtualWalletPo getVirtualWalletById(Integer walletId);
}

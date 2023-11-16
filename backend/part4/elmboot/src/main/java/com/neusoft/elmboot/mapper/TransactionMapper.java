package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.TransactionPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface TransactionMapper {

    @Insert("insert into transaction(time,money,type,inputwalletId,outputwalletId) values(#{time},#{money},#{type},#{inputwalletId},#{outputwalletId})")
    @Options(useGeneratedKeys = true, keyProperty = "transactionId")
    public int writeTransaction(TransactionPo transactionPo);
}

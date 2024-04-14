package com.tju.elmcloud.mapper;

import com.tju.elmcloud.po.TransactionPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {
    public int writeTransaction(TransactionPo transactionPo);
}
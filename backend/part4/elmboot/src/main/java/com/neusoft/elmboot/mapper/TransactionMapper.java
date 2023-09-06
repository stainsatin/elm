package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.TransactionPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {
    public int writeTransaction(TransactionPo transactionPo);
}

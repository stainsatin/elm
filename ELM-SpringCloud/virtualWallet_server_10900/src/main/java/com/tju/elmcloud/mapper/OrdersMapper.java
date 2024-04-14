package com.tju.elmcloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrdersMapper {
	@Update("update orders set orderState=1 where orderId=#{orderId}")
	public int payOrders(Integer orderId);
}

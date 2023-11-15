package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.entity.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrdersMapper {

	@Insert("insert into orders (userId,businessId,orderDate,orderTotal,daId,orderState) values(#{userId},#{businessId},#{orderDate},#{orderTotal},#{daId},#{orderState})")
	@Options(useGeneratedKeys = true, keyProperty = "orderId", keyColumn = "orderId")
	public void saveOrders(Orders orders);

	@Select("select * from orders where orderId=#{orderId}")
	public Orders getOrdersById(Integer orderId);

	@Select("select * from orders where userId=#{userId}")
	public List<Orders> listOrdersByUserId(String userId);

	@Update("update orders set orderState=1 where orderId=#{orderId}")
	public int payOrders(Integer orderId);
}

package com.neusoft.elmboot.mapper;

import java.util.List;

import com.neusoft.elmboot.po.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrdersMapper {
	@Insert("insert into orders (userId,businessId,orderDate,orderTotal,daId) values(#{userId},#{businessId},#{orderDate},#{orderTotal},#{daId})")
	@Options(useGeneratedKeys=true,keyProperty="orderId",keyColumn="orderId")
	public int saveOrders(Orders orders);
	public Orders getOrdersById(Integer orderId);
	public List<Orders> listOrdersByUserId(String userId);
	@Update("update orders set orderState=1 where orderId=#{orderId}")
	public int payOrders(Integer orderId);
}

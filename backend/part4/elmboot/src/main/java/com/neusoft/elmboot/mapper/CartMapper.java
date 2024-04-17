package com.neusoft.elmboot.mapper;

import java.util.List;

import com.neusoft.elmboot.po.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
@Mapper
public interface CartMapper {
	@Insert("insert into cart values(null,#{foodId},#{businessId},#{userId},1)")
	public int saveCart(Cart cart);
	
	@Update("update cart set quantity=#{quantity} where userId=#{userId} and businessId=#{businessId} and foodId=#{foodId}")
	public int updateCart(Cart cart);
	
	public int removeCart(Cart cart);
	public List<Cart> listCart(Cart cart);
}

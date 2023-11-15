package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {
    @Insert("insert into cart values(null,#{foodId},#{businessId},#{userId},1)")
    public int saveCart(Cart cart);

    @Select("select * from cart where foodId=#{foodId} and businessId=#{businessId} and userId=#{userId}")
    public Cart getCart(Integer foodId, Integer businessId, String userId);

    @Update("update cart set quantity=#{quantity} where foodId=#{foodId} and businessId=#{businessId} and userId=#{userId}")
    public int updateCart(Cart cart);

    @Delete("delete from cart where foodId=#{foodId} and businessId=#{businessId} and userId=#{userId}")
    public void removeCart(Cart cart);

    @Select("select * from cart where userId=#{userId}")
    public List<Cart> getCartByUserId(String userId);

}

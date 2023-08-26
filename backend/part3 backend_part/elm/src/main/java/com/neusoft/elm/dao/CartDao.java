package com.neusoft.elm.dao;
 import com.neusoft.elm.po.Cart;
public interface CartDao {
	public int saveCart(Cart cart)throws Exception;
	public int updateCart(Cart cart)throws Exception;
	public int removeCart(Cart cart)throws Exception;
}

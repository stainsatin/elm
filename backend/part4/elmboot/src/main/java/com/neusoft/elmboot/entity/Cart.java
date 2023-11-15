package com.neusoft.elmboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cart implements Serializable{
        private static final long serialVersionUID = 1203532745189077918L;
		private Integer cartId;
        private Integer foodId;
        private Integer businessId;
        private String userId;
        private Integer quantity;


    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}

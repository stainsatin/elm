package com.neusoft.elmboot.service;

import com.neusoft.elmboot.entity.Food;

import java.util.List;
public interface FoodService {
    public List<Food> listFoodByBusinessId(Integer businessId);
}
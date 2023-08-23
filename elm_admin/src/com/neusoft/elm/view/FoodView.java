package com.neusoft.elm.view;

import java.util.List;

import com.neusoft.elm.po.Food;

public interface FoodView {

	public List<Food> showFoodList(Integer businessId);
	public void saveFood(Integer businessId);
	public void updateFood(Integer businessId);
	public void removeFood(Integer businessId);
}

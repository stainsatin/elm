package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.dto.Result;
import com.neusoft.elmboot.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController {
	@Autowired
	private FoodService foodService;

	@GetMapping
	public Result listFoodByBusinessId(Integer businessId) throws Exception {
		return Result.success(foodService.listFoodByBusinessId(businessId));
	}
}

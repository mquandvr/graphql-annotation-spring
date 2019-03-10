package com.mqdvr.graphql.service;

import com.mqdvr.graphql.dto.FoodDto;
import com.mqdvr.graphql.dto.StoreDto;
import com.mqdvr.graphql.service.base.BaseService;

public interface StoreService extends BaseService<StoreDto, Long> {

	FoodDto findByFoodId(StoreDto store, Long foodId);
}

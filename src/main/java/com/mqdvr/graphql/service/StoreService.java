package com.mqdvr.graphql.service;

import com.mqdvr.graphql.dto.Food;
import com.mqdvr.graphql.dto.Store;
import com.mqdvr.graphql.service.base.BaseService;

public interface StoreService extends BaseService<Store, Long> {

	Food findByFoodId(Store store, Long foodId);
}

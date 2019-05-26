package fjs.co.graphql.service;

import fjs.co.graphql.base.BaseService;
import fjs.co.graphql.dto.Food;
import fjs.co.graphql.dto.Store;

public interface StoreService extends BaseService<Store, Long> {

	Food findByFoodId(Store store, Long foodId);
}

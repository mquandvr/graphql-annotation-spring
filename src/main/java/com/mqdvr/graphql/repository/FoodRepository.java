package com.mqdvr.graphql.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mqdvr.graphql.entity.FoodEntity;

public interface FoodRepository extends CrudRepository<FoodEntity, Long> {

	@Query(value = "SELECT * FROM FOOD WHERE FOOD_ID = :foodId AND STORE_ID = :storeId",
			  nativeQuery = true)
	FoodEntity findByFoodIdAndStoreId(@Param("foodId") Long foodId,@Param("storeId") Long StoreId);
}

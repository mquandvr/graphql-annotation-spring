package com.mqdvr.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mqdvr.graphql.entity.FoodEntity;
import com.mqdvr.graphql.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

}

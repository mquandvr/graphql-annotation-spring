package com.mqdvr.graphql.service;

import com.mqdvr.graphql.dto.Food;
import com.mqdvr.graphql.service.base.BaseService;

import io.leangen.graphql.annotations.types.GraphQLInterface;

public interface FoodService extends BaseService<Food, Long> {
}

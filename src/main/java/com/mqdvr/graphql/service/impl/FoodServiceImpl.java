package com.mqdvr.graphql.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.mqdvr.graphql.dto.Food;
import com.mqdvr.graphql.entity.FoodEntity;
import com.mqdvr.graphql.repository.FoodRepository;
import com.mqdvr.graphql.service.FoodService;

import graphql.execution.batched.Batched;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    @GraphQLQuery(name = "findAllFood")
    public List<Food> findAll() {
        Iterable<FoodEntity> foodLst = foodRepository.findAll();
        List<Food> foodDto = new ArrayList<Food>();

        foodLst.forEach(entity -> {
            Food dto = converEntityToDto(entity);

            foodDto.add(dto);
        });

        return foodDto;
    }

    @Override
    @GraphQLQuery(name = "findFoodById")
    public Food findById(@GraphQLArgument(name = "id", description = "food id") @GraphQLNonNull long foodId) {
        Optional<FoodEntity> entity = foodRepository.findById(foodId);

        return converEntityToDto(entity.orElse(null));
    }

    @Override
    public Long updateById(Food t, long id) {
        // TODO Auto-generated method stub
        return null;
    }

    private Food converEntityToDto(FoodEntity entity) {
        if (entity == null) {
            return null;
        }

        Food dto = new Food();
        dto.setId(entity.getId());
        dto.setFoodName(entity.getFoodName());
        dto.setType(entity.getType());

        return dto;
    }

}

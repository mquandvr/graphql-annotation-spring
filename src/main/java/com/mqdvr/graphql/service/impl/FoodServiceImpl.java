package com.mqdvr.graphql.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.mqdvr.graphql.dto.FoodDto;
import com.mqdvr.graphql.entity.FoodEntity;
import com.mqdvr.graphql.repository.FoodRepository;
import com.mqdvr.graphql.service.FoodService;

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
    public List<FoodDto> findAll() {
        Iterable<FoodEntity> foodLst = foodRepository.findAll();
        List<FoodDto> foodDto = new ArrayList<FoodDto>();

        foodLst.forEach(entity -> {
            FoodDto dto = converEntityToDto(entity);

            foodDto.add(dto);
        });

        return foodDto;
    }

    @Override
    @GraphQLQuery(name = "findFoodById")
    public FoodDto findById(@GraphQLArgument(name = "id") @GraphQLNonNull long foodId) {
        Optional<FoodEntity> entity = foodRepository.findById(foodId);

        return converEntityToDto(entity.orElse(null));
    }

    @Override
    public Long updateById(FoodDto t, long id) {
        // TODO Auto-generated method stub
        return null;
    }

    private FoodDto converEntityToDto(FoodEntity entity) {
        if (entity == null) {
            return null;
        }

        FoodDto dto = new FoodDto();
        dto.setId(entity.getId());
        dto.setFoodName(entity.getFoodName());
        dto.setType(entity.getType());

        return dto;
    }

}

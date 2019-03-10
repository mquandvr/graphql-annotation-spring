package com.mqdvr.graphql.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.mqdvr.graphql.dto.FoodDto;
import com.mqdvr.graphql.dto.StoreDto;
import com.mqdvr.graphql.entity.FoodEntity;
import com.mqdvr.graphql.entity.StoreEntity;
import com.mqdvr.graphql.repository.FoodRepository;
import com.mqdvr.graphql.repository.StoreRepository;
import com.mqdvr.graphql.service.StoreService;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Override
    @GraphQLQuery(name = "findAllStore")
    public List<StoreDto> findAll() {
        Iterable<StoreEntity> stores = storeRepository.findAll();
        List<StoreDto> storeDtos = new ArrayList<StoreDto>();

        stores.forEach(entity -> {
            StoreDto dto = converStoreEntityToDto(entity);
            storeDtos.add(dto);
        });

        return storeDtos;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GraphQLQuery(name = "findByFoodId")
    public FoodDto findByFoodId(@GraphQLContext StoreDto store,
            @GraphQLArgument(name = "foodId") @GraphQLNonNull Long foodId) {
        FoodEntity entity = foodRepository.findByFoodIdAndStoreId(foodId, store.getId());
        FoodDto dto = converFoodEntityToDto(entity);
        return dto;
    }

    @Override
    public StoreDto findById(long id) {
        Optional<StoreEntity> entity = storeRepository.findById(id);

        return converStoreEntityToDto(entity.orElse(null));
    }

    @Override
    public Long updateById(StoreDto t, long id) {
        // TODO Auto-generated method stub
        return null;
    }


    private StoreDto converStoreEntityToDto(StoreEntity entity) {
        if (entity == null) {
            return null;
        }

        StoreDto dto = new StoreDto();
        dto.setId(entity.getId());
        dto.setStoreName(entity.getStoreName());

        List<FoodDto> foods =new ArrayList<FoodDto>();
        entity.getListFood().forEach(food -> {
            FoodDto foodDto = converFoodEntityToDto(food);
            foods.add(foodDto);
        });
        dto.setListFood(foods);

        return dto;
    }


    private FoodDto converFoodEntityToDto(FoodEntity entity) {
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

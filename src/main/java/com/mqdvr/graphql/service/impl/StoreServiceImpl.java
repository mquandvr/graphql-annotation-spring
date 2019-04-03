package com.mqdvr.graphql.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mqdvr.graphql.dto.Food;
import com.mqdvr.graphql.dto.Store;
import com.mqdvr.graphql.entity.FoodEntity;
import com.mqdvr.graphql.entity.StoreEntity;
import com.mqdvr.graphql.repository.FoodRepository;
import com.mqdvr.graphql.repository.StoreRepository;
import com.mqdvr.graphql.service.StoreService;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Override
    @GraphQLQuery(name = "findAllStore")
    public List<Store> findAll() {
        Iterable<StoreEntity> stores = storeRepository.findAll();
        List<Store> storeDtos = new ArrayList<Store>();

        stores.forEach(entity -> {
            Store dto = converStoreEntityToDto(entity);
            storeDtos.add(dto);
        });

        return storeDtos;
    }

    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GraphQLQuery(name = "findByFoodId")
    public Food findByFoodId(@GraphQLContext Store store,
            @GraphQLArgument(name = "foodId") @GraphQLNonNull Long foodId) {
        FoodEntity entity = foodRepository.findByFoodIdAndStoreId(foodId, store.getId());
        Food dto = converFoodEntityToDto(entity);
        return dto;
    }

    @Override
    public Store findById(long id) {
        Optional<StoreEntity> entity = storeRepository.findById(id);

        return converStoreEntityToDto(entity.orElse(null));
    }

    @Override
    public Long updateById(Store t, long id) {
        // TODO Auto-generated method stub
        return null;
    }


    private Store converStoreEntityToDto(StoreEntity entity) {
        if (entity == null) {
            return null;
        }

        Store dto = new Store();
        dto.setId(entity.getId());
        dto.setStoreName(entity.getStoreName());

        List<Food> foods =new ArrayList<Food>();
        entity.getListFood().forEach(food -> {
            Food foodDto = converFoodEntityToDto(food);
            foods.add(foodDto);
        });
        dto.setListFood(foods);

        return dto;
    }


    private Food converFoodEntityToDto(FoodEntity entity) {
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

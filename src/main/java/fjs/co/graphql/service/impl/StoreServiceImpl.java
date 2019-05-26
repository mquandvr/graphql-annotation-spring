package fjs.co.graphql.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import fjs.co.graphql.base.AbstractService;
import fjs.co.graphql.dto.Food;
import fjs.co.graphql.dto.Store;
import fjs.co.graphql.entity.FoodEntity;
import fjs.co.graphql.entity.StoreEntity;
import fjs.co.graphql.repository.FoodRepository;
import fjs.co.graphql.repository.StoreRepository;
import fjs.co.graphql.service.StoreService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.ResolutionEnvironment;

@Service
public class StoreServiceImpl extends AbstractService implements StoreService {

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

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//    @Secured("ROLE_ADMIN")
    @GraphQLQuery(name = "findAllStoress")
//    @Auth(requiredRole = "ADMIN")
    public List<Store> findAll(@GraphQLEnvironment ResolutionEnvironment env) {
        String role = getRole(env);
//        if (auth != null && !currentUser.getRoles().containsAll(Arrays.asList(auth.requiredRole()))) {
//        if (auth != null && !request.isUserInRole(auth.requiredRole())) {
//            throw new IllegalAccessException("Access denied"); // or return null
//        }
        Iterable<StoreEntity> stores = storeRepository.findAll();
        List<Store> storeDtos = new ArrayList<Store>();

        stores.forEach(entity -> {
            Store dto = converStoreEntityToDto(entity);
            storeDtos.add(dto);
        });

        return storeDtos;
    }

    private String getRole(ResolutionEnvironment env) {
        HttpServletRequest request = env.dataFetchingEnvironment.getContext();
//        Authentication  user = (Authentication ) request.getUserPrincipal();
//        user.getAuthorities().retainAll(c)
        return request.getUserPrincipal().toString();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

        BeanUtils.copyProperties(entity, dto, "listFood");
//        dto.setId(entity.getId());
//        dto.setStoreName(entity.getStoreName());
//
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

        BeanUtils.copyProperties(entity, dto);

//        dto.setId(entity.getId());
//        dto.setFoodName(entity.getFoodName());
//        dto.setType(entity.getType());
//        dto.setCertification(entity.getCertification());

        return dto;
    }

}

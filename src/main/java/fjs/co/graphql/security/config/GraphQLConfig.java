/*
 * File: GraphQLConfig.java
 *
 * Copyright (c) 2019
 */
package fjs.co.graphql.security.config;

import java.util.Arrays;

import fjs.co.graphql.service.FoodService;
import fjs.co.graphql.service.StoreService;
import fjs.co.graphql.service.UserService;
import fjs.co.graphql.service.impl.FoodServiceImpl;
import fjs.co.graphql.service.impl.StoreServiceImpl;
import fjs.co.graphql.service.impl.UserServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.GraphQL;
import graphql.analysis.MaxQueryComplexityInstrumentation;
import graphql.analysis.MaxQueryDepthInstrumentation;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;

/**
 * GraphQL config init.
 * @author quan-ppm
 * @version 1.0
 */
@Configuration
public class GraphQLConfig {

    /**
     * Build graphQL with service.
     * @param foodService
     * @param storeService
     * @param userService
     * @return graphQL
     */
    @Bean
    public GraphQL graphQL(FoodService foodService,
                                StoreService storeService,
                                UserService userService) {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(
                        //Resolve by annotations
                        new AnnotatedResolverBuilder())
//                .withOperationsFromSingleton(userService, new TypeToken<GenericSer>(){}.getType())
                .withOperationsFromSingleton(userService, UserServiceImpl.class)
                .withOperationsFromSingleton(storeService, StoreServiceImpl.class)
                .withOperationsFromSingleton(foodService, FoodServiceImpl.class)
                .withResolverInterceptors(new AuthInterceptor())
//                .withOperationsFromSingletons(userService, foodService, storeService)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
        return GraphQL
                    .newGraphQL(schema)
//                    .queryExecutionStrategy(new BatchedExecutionStrategy())
                    .instrumentation(new ChainedInstrumentation(Arrays.asList(
                            new MaxQueryComplexityInstrumentation(200),
                            new MaxQueryDepthInstrumentation(20)
                    )))
                    .build();
    }

}

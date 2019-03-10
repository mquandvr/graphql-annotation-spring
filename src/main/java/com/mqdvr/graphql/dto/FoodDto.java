package com.mqdvr.graphql.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.security.access.prepost.PreAuthorize;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FoodDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @GraphQLQuery(name = "foodId", description = "food id")
    private Long id;

    @GraphQLQuery(name = "foodName", description = "food name")
    private String foodName;

    @GraphQLQuery(name = "type", description = "food type")
    private String type;

    @GraphQLQuery(name = "certification", description = "certification")
    private String certification;

    @GraphQLQuery(name = "createdDate", description = "created date")
    private Date createdDate;

    @GraphQLQuery(name = "createBy", description = "created by")
    private String createBy;

    @GraphQLQuery(name = "updatedDate", description = "update date")
    private Date updatedDate;

    @GraphQLQuery(name = "updateBy", description = "updated by")
    private String updateBy;
}

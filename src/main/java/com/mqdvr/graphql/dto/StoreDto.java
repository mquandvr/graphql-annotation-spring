package com.mqdvr.graphql.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StoreDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@GraphQLQuery(name = "storeId", description = "store id")
	private Long id;

	@GraphQLQuery(name = "storeName", description = "store name")
	private String storeName;

	@GraphQLQuery(name = "foods", description = "list food")
	private List<FoodDto> listFood;

	@GraphQLQuery(name = "createdDate", description = "created date")
	private Date createdDate;

	@GraphQLQuery(name = "createBy", description = "created by")
	private String createBy;

	@GraphQLQuery(name = "updatedDate", description = "update date")
	private Date updatedDate;

	@GraphQLQuery(name = "updateBy", description = "updated by")
	private String updateBy;

}
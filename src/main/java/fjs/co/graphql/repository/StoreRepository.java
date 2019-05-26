package fjs.co.graphql.repository;

import fjs.co.graphql.entity.StoreEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

}

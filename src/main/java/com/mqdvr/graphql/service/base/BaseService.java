package com.mqdvr.graphql.service.base;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public interface BaseService<T, R> {

    List<T> findAll();

    T findById(long id);

    R updateById(T t, long id);
}

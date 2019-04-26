package fjs.co.graphql.base;

import java.util.List;

public interface BaseService<T, R> {

    List<T> findAll();

    T findById(long id);

    R updateById(T t, long id);
}

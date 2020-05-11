package com.lesson3.DAO;

import com.lesson3.BadRequestException;

public interface DAO<T> {
    T findById(Long id) throws BadRequestException;
    T save(T t);
    T update(T t);
    T delete(T t);

}

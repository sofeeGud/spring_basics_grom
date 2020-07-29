package com.lesson6.hw.dao;

import com.lesson6.hw.BadRequestException;


public interface GeneralDAO<T> {
    T findById(long id) throws BadRequestException, Exception;

    T save(T t) throws Exception;

    T update(T t) throws BadRequestException, Exception;

    void delete(long id) throws BadRequestException, Exception;
}

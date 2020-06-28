package com.lesson6.hw.dao;

import com.lesson6.hw.BadRequestException;


public interface GeneralDAO<T> {
    T findById(long id) throws BadRequestException;

    T save(T t) throws BadRequestException;

    T update(T t) throws BadRequestException;

    void delete(long id) throws BadRequestException;
}

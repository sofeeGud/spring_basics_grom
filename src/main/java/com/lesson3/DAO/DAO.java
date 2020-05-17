package com.lesson3.DAO;

import com.lesson3.BadRequestException;
import javassist.NotFoundException;

public interface DAO<T> {
    T findById(long id) throws NotFoundException;

    T save(T t);

    T update(T t) throws BadRequestException;

    void delete(long id) throws BadRequestException, NotFoundException;

}

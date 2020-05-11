package com.lesson3.DAO;

import com.lesson3.BadRequestException;
import com.lesson3.model.Storage;
import org.hibernate.Session;

public interface StorageDAO extends DAO<Storage>{

    void changeSizeUp(Long id, Long size, Session session) throws BadRequestException;
    void changeSizeDown(Long id, Long size, Session session) throws BadRequestException;

}

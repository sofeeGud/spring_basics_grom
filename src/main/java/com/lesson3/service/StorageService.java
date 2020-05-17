package com.lesson3.service;

import com.lesson3.BadRequestException;
import com.lesson3.DAO.StorageDAO;
import com.lesson3.model.Storage;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class StorageService{

    private StorageDAO storageDAO;

    @Autowired
    public StorageService(StorageDAO storageDAO) {

        this.storageDAO = storageDAO;
    }
    public Storage save(Storage storage) {

        return storageDAO.save(storage);
    }
    public Storage findById(long id) throws Exception{

        return storageDAO.findById(id);
    }
    public Storage update(Storage storage) throws BadRequestException {

        return storageDAO.update(storage);
    }
    public void delete(long id) throws BadRequestException, NotFoundException {

        storageDAO.delete(id);
    }
}

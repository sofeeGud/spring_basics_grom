package com.lesson3.service;

import com.lesson3.BadRequestException;
import com.lesson3.model.File;
import com.lesson3.model.Storage;
import org.hibernate.Session;

public interface Service {
    File put(Storage storage, File file, Session session) throws BadRequestException;
    File delete(Storage storage, File file, Session session) throws BadRequestException;
    void transferAll(Storage storageFrom, Storage storageTo) throws BadRequestException;
    void transferFile(Storage storageFrom, Storage storageTo, Long id, Session session) throws BadRequestException;
}

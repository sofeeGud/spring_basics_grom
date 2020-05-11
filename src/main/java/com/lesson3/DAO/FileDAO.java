package com.lesson3.DAO;

import com.lesson3.BadRequestException;
import com.lesson3.model.File;
import com.lesson3.model.Storage;
import org.hibernate.Session;

import java.util.List;

public interface FileDAO extends DAO<File> {
    File put(Storage storage, File file, Session session) throws BadRequestException;
    File delete(Storage storage, File file, Session session) throws BadRequestException;
    void transferFiles(Storage storageFrom, Storage storageTo, long filesSize) throws BadRequestException;
    void transferFile(Storage storageFrom, Storage storageTo, File file, Session session) throws BadRequestException;
    List<File> getFilesByStorageId(Long id) throws BadRequestException;
}

package com.lesson3.service;

import com.lesson3.BadRequestException;
import com.lesson3.DAO.FileDAO;
import com.lesson3.DAO.StorageDAO;
import com.lesson3.model.File;
import com.lesson3.model.Storage;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceImpl implements Service {

    private FileDAO fileDAO;
    private StorageDAO storageDAO;

    @Autowired
    public ServiceImpl(FileDAO fileDAO, StorageDAO storageDAO) {
        this.fileDAO = fileDAO;
        this.storageDAO = storageDAO;
    }

    @Override
    public File put(Storage storage, File file, Session session) throws BadRequestException {
        file.setStorage(storage);
        validateFile(storage, file);
        return fileDAO.put(storage, file, session);
    }

    @Override
    public File delete(Storage storage, File file, Session session) throws BadRequestException {
        file.setStorage(storage);
        return fileDAO.delete(storage, file, session);
    }

    @Override
    public void transferAll(Storage storageFrom, Storage storageTo) throws BadRequestException {

        long filesSize = 0;
        long storageSize = storageTo.getStorageSize();
        for (File file : fileDAO.getFilesByStorageId(storageFrom.getId())) {
            filesSize += file.getSize();
            storageSize -= file.getSize();

            checkFileFormat(storageTo, file);
            if (storageSize < 0)
                throw new BadRequestException(getClass().getName() + "-transferAll. Storage is full. storage id:" + storageTo.getId() + (file.getId() == 0 ? "" : " file id:" + file.getId()));
        }

        fileDAO.transferFiles(storageFrom, storageTo, filesSize);
    }

    @Override
    public void transferFile(Storage storageFrom, Storage storageTo, Long id, Session session) throws BadRequestException {

        File file = fileDAO.findById(id);
        file.setStorage(storageTo);
        validateFile(storageTo, file);
        fileDAO.transferFile(storageFrom, storageTo, file, session);
    }

    private void validateFile(Storage storage, File file) throws BadRequestException{
        file.setStorage(storage);
        if(storage.getStorageSize() < file.getSize())
            throw new BadRequestException(getClass().getName()+"-checkInputFileSize. There is no enough free space in storage id:"+storage.getId()+" file id:"+file.getId());
    }

    private void checkFileFormat(Storage storage, File file) throws BadRequestException {
        for(String format : storage.getFormatsSupported())
            if(format.equals(file.getFormat()))
                return;
        throw new BadRequestException(getClass().getName()+"-checkInputFileFormat. File format is not accepted. storage id:"+storage.getId()+" file id:"+file.getId());
    }
}

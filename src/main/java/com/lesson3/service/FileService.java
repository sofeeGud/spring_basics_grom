package com.lesson3.service;

import com.lesson3.BadRequestException;
import com.lesson3.DAO.FileDAO;
import com.lesson3.DAO.StorageDAO;
import com.lesson3.model.File;
import com.lesson3.model.Storage;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FileService {

    private FileDAO fileDAO;
    private StorageDAO storageDAO;

    @Autowired
    public FileService(FileDAO fileDAO, StorageDAO storageDAO) {

        this.fileDAO = fileDAO;
        this.storageDAO = storageDAO;
    }

    public File save(File file) throws Exception {

        if (file.getName().length() > 10)
            throw new BadRequestException("File name id: " + file.getId() + " is to save, used name < 10 symbols");
        return fileDAO.save(file);
    }

    public File findById(long id) throws NotFoundException, BadRequestException {

        return fileDAO.findById(id);
    }

    public File update(File file) throws BadRequestException {

        return fileDAO.update(file);
    }

    public void delete(long id) throws BadRequestException {

        fileDAO.delete(id);
    }

    public File put(long storageId, long fileId) throws Exception {

        File file = fileDAO.findById(fileId);

        if (file.getStorage() != null)
            throw new BadRequestException("File id: " + fileId + " is already exists in storage " +
                    file.getStorage().getId());

        Storage storageTo = storageDAO.findById(storageId);
        List<File> filesEx = fileDAO.getFilesByStorage(storageId);

        validatePut(file, filesEx, storageTo);

        file.setStorage(storageTo);
        return update(file);
    }

    public void delete(long storageId, long fileId) throws Exception {

        File file = findById(fileId);
        Storage storage = storageDAO.findById(storageId);

        if (!storage.equals(file.getStorage()))
            throw new NotFoundException("File id:" + fileId + " was not found in storage id: " + storageId);

        file.setStorage(null);
        update(file);
    }

    public void transferFile(long storageFromId, long storageToId, long id) throws Exception {

        File file = findById(id);
        Storage storageFrom = storageDAO.findById(storageFromId);

        if (!storageFrom.equals(file.getStorage()))
            throw new BadRequestException("File id: " + id + " was not found in storage id: " + storageFromId);

        Storage storageTo = storageDAO.findById(storageToId);
        List<File> filesEx = fileDAO.getFilesByStorage(storageToId);

        validatePut(file, filesEx, storageTo);

        file.setStorage(storageTo);
        update(file);
    }

    public int transferAll(long storageFromId, long storageToId) throws Exception {

        Storage storageTo = storageDAO.findById(storageToId);
        List<File> filesFrom = fileDAO.getFilesByStorage(storageFromId);
        List<File> filesTo = fileDAO.getFilesByStorage(storageToId);

        if (filesFrom.size() == 0)
            throw new NotFoundException("Not found files for transfer");

        validatePut(filesFrom, filesTo, storageTo);

        return fileDAO.transferAll(storageFromId, storageToId);
    }

    private void validatePut(File filePut, List<File> filesFrom, Storage storage) throws Exception {

        validation(filePut, storage, getFreeSpace(storage, filesFrom));
    }

    private void validatePut(List<File> filesPut, List<File> filesFrom, Storage storage) throws Exception {

        long checkFreeSpace = getFreeSpace(storage, filesFrom);

        for (File file : filesPut) {
            validation(file, storage, checkFreeSpace);
            checkFreeSpace -= file.getSize();
        }
    }

    private long getFreeSpace(Storage storage, List<File> files) {

        long freeSpace = storage.getStorageSize();
        for (File file : files) freeSpace -= file.getSize();
        return freeSpace;
    }

    private void validation(File file, Storage storage, long freeSpace) throws Exception {
        if (file.getSize() > freeSpace)
            throw new BadRequestException("Filed put file to storage id: " + storage.getId() +
                    " no enough free space");

        if (!storage.getFormatsSupported().equals(file.getFormat()))
            throw new BadRequestException("Filed put file to storage id: " + storage.getId() +
                    "format is wrong  " + file.getFormat());
    }
}

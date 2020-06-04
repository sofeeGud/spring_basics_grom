package com.lesson3.service;

import com.lesson3.BadRequestException;
import com.lesson3.DAO.FileDAO;
import com.lesson3.DAO.StorageDAO;
import com.lesson3.model.File;
import com.lesson3.model.Storage;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

    public File put(File file, Storage storage) throws Exception {
        file.setStorage(storage);
        validatePut(storage, file);

        return fileDAO.put(storage, file);
    }

    public void delete(long storageId, long fileId) throws Exception {

        File file = findById(fileId);
        Storage storage = storageDAO.findById(storageId);

        if (!storage.equals(file.getStorage()))
            throw new NotFoundException("File id:" + fileId + " was not found in storage id: " + storageId);

        fileDAO.delete(fileId);
    }

    public void transferFile(long storageToId, long id) throws Exception {

        Storage storageTo = storageDAO.findById(storageToId);
        File file = fileDAO.findById(id);
        validatePut(storageTo, file);
        file.setStorage(storageTo);
        update(file);
    }

    public int transferAll(long storageFromId, long storageToId) throws Exception {
        Storage storageTo = storageDAO.findById(storageToId);
        long filesSize = fileDAO.getFilesSize(storageFromId);
        long storageSize = storageTo.getStorageSize();

        if (storageSize < filesSize) {
            throw new BadRequestException("No free space in storage id " + storageToId);
        }
        return fileDAO.transferAll(storageFromId, storageToId);
    }

    private void validatePut(Storage storage, File file) throws BadRequestException {

        validateStorageSize(storage, file);
        checkFileFormat(storage, file);
    }

    private void checkFileFormat(Storage storage, File file) throws BadRequestException {
        for (String format : storage.getFormatsSupported().split(","))
            if (format.equals(file.getFormat()))
                return;
        throw new BadRequestException(getClass().getName() + " File format is not accepted. storage id:" + storage.getId() + " file id:" + file.getId());
    }


    public boolean validateStorageSize(Storage storage, File file) throws BadRequestException {
        if (storage != null && file != null)
            return ((storage.getStorageSize() - fileDAO.getFilesSize(storage.getId())) > file.getSize());
        else if (storage == null) throw new BadRequestException("There is not storage " + storage.getId());
        else throw new BadRequestException("Something wrong");
    }

}

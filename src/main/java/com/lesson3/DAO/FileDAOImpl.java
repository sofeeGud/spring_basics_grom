package com.lesson3.DAO;


import com.lesson3.BadRequestException;
import com.lesson3.model.File;
import com.lesson3.model.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FileDAOImpl extends DAOImpl<File> implements FileDAO {

    private StorageDAO storageDAO;

    @Autowired
    public FileDAOImpl(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
        setClazz(File.class);
    }


    @Override
    public File put(Storage storage, File file, Session session) throws BadRequestException {
        save(file);
        storageDAO.changeSizeUp(storage.getId(), file.getSize(), session);
        return file;
    }

    @Override
    public File delete(Storage storage, File file, Session session) throws BadRequestException {
         delete(file);
        storageDAO.changeSizeDown(storage.getId(), file.getSize(), session);
        return file;
    }

    @Override
    public void transferFiles(Storage storageFrom, Storage storageTo, long filesSize) throws BadRequestException {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            Query query = session.createSQLQuery("UPDATE FILE SET STORAGE_ID = :storageTo WHERE STORAGE_ID = :storageFrom");
            query.setParameter("storageTo", storageTo.getId());
            query.setParameter("storageFrom", storageFrom.getId());
            storageDAO.changeSizeUp(storageFrom.getId(), filesSize, session);
            storageDAO.changeSizeDown(storageTo.getId(), filesSize, session);

            session.getTransaction().commit();
        }catch (HibernateException e){
            if (transaction != null)
                transaction.rollback();
            throw new HibernateException(getClass().getName()+"-transferFiles. Transfer fail from storage id:"+storageFrom.getId()+" to id:"+storageTo.getId()+". "+e.getMessage());
        }
    }

    @Override
    public void transferFile(Storage storageFrom, Storage storageTo, File file, Session session) throws BadRequestException {
        update(file);
        storageDAO.changeSizeUp(storageTo.getId(), file.getSize(), session);
        storageDAO.changeSizeDown(storageFrom.getId(), file.getSize(), session);

    }

    @Override
    public List<File> getFilesByStorageId(Long id) throws BadRequestException {
        try (Session session = createSessionFactory().openSession()) {
            return (List<File>) session.createSQLQuery("SELECT * FROM FILE WHERE STORAGE_ID = :storageId")
                    .setParameter("storageId", id)
                    .addEntity(File.class).list();
        } catch (HibernateException e) {
            throw new HibernateException(getClass().getName() + "-getFilesByStorageId. " + e.getMessage());
        }
    }
}

package com.lesson3.DAO;

import com.lesson3.BadRequestException;
import com.lesson3.model.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class StorageDAOImpl extends DAOImpl<Storage> implements StorageDAO {

    public StorageDAOImpl() {
        setClazz(Storage.class);
    }
    @Override
    public void changeSizeUp(Long id, Long size, Session session) throws BadRequestException {
        try{
            Query query = session.createSQLQuery("UPDATE STORAGE SET STORAGE_SIZE = STORAGE_SIZE+ :size WHERE ID = :storageId");
            query.setParameter("storageSize", size);
            query.setParameter("storageId", id);
            if(query.executeUpdate() == 0)
                throw new BadRequestException("Storage size with id "+id+" was not updated");
        } catch (HibernateException e){
            throw new HibernateException("Storage size with id "+id+" was not updated. "+e.getMessage());
        }

    }

    @Override
    public void changeSizeDown(Long id, Long size, Session session) throws BadRequestException {

        try{
            Query query = session.createSQLQuery("UPDATE STORAGE SET STORAGE_SIZE = STORAGE_SIZE- :size WHERE ID = :storageId");
            query.setParameter("storageSize", size);
            query.setParameter("storageId", id);
            if(query.executeUpdate() == 0)
                throw new BadRequestException("Storage size with id "+id+" was not updated");
        } catch (HibernateException e){
            throw new HibernateException("Storage size with id "+id+" was not updated. "+e.getMessage());
        }
    }

}

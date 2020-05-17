package com.lesson3.DAO;

import com.lesson3.model.File;
import javassist.NotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class FileDAO implements DAO<File>{
    private SessionFactory sessionFactory;

@Override
    public File save(File file) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(file);
            session.getTransaction().commit();
            return file;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("Save : " + file.getId() + " failed" + e.getMessage());
        }

    }

@Override
    public File findById(long id) throws NotFoundException {

        try (Session session = createSessionFactory().openSession()) {
            return session.get(File.class, id);

        } catch (HibernateException e) {
            throw new NotFoundException(getClass().getSimpleName() + "-findById: " + id + " failed. " + e.getMessage());
        }
    }

@Override
    public File update(File file) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(file);
            session.getTransaction().commit();
            return file;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("Update : " + file.getId() + " failed" + e.getMessage());
        }
    }

 @Override
    public void delete(long id) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (HibernateException | NotFoundException e) {
            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("Delete with fileId: " + id + " failed" + e.getMessage());
        }
    }
    public int transferAll(long storageFromId, long storageToId) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE File file SET file.storage.id = :storageToId WHERE file.storage.id = :storageFromId");

        query.setParameter("storageToId", storageToId);
        query.setParameter("storageFromId", storageFromId);

        return query.executeUpdate();
    }

    public List<File> getFilesByStorage(Long storageId) {

        Session session = sessionFactory.getCurrentSession();
        Query<File> query = session.createQuery("SELECT file FROM File file WHERE file.storage.id = : storageId", File.class);

        query.setParameter("storageId", storageId);
        return query.getResultList();
    }

    public SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
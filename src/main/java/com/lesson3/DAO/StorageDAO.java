package com.lesson3.DAO;

import com.lesson3.BadRequestException;
import com.lesson3.model.Storage;
import javassist.NotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository

public class StorageDAO implements DAO<Storage> {
    private SessionFactory sessionFactory;

    @Override
    public Storage save(Storage storage) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(storage);
            session.getTransaction().commit();
            return storage;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("Save : " + storage.getId() + " failed" + e.getMessage());
        }

    }

    @Override
    public Storage findById(long id) throws NotFoundException {
        try (Session session = createSessionFactory().openSession()) {
            return session.get(Storage.class, id);

        } catch (HibernateException e) {
            throw new NotFoundException(getClass().getSimpleName() + "-findById: " + id + " failed. " + e.getMessage());
        }
    }

    @Override
    public Storage update(Storage storage) throws BadRequestException {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(storage);
            session.getTransaction().commit();
            return storage;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("Update: " + storage.getId() + " failed" + e.getMessage());
        }
    }

    @Override
    public void delete(long id) throws BadRequestException, NotFoundException {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("Delete with storageId: " + id + " failed" + e.getMessage());
        }
    }

    public SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}

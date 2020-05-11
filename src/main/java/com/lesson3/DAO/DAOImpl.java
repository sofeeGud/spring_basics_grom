package com.lesson3.DAO;

import com.lesson3.BadRequestException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public abstract class DAOImpl<T> implements DAO<T> {

    private Class<T> clazz;
    private SessionFactory sessionFactory;

    public final void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @Override
    public T findById(Long id) throws BadRequestException {
        try (Session session = createSessionFactory().openSession()) {
            return session.get(clazz, id);

        } catch (HibernateException e) {
            throw new BadRequestException(getClass().getSimpleName() + "-findById: " + id + " failed. " + e.getMessage());
        }
    }

    @Override
    public T save(T t) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            session.save(t);

            session.getTransaction().commit();
            System.out.println(t.getClass().getName() + " saved with id:" + t.getClass().getSimpleName());
            return t;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("Save " + t.getClass().getSimpleName() + ": " + t.toString() + " failed" + e.getMessage());
        }
    }

    @Override
    public T update(T t) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            session.update(t);

            session.getTransaction().commit();
            System.out.println(t.getClass().getSimpleName() + " updated");
            return t;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("Update " + t.getClass().getSimpleName() + ": " + t.toString() + " failed" + e.getMessage());
        }
    }

    @Override
    public T delete(T t) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            session.delete(t);

            session.getTransaction().commit();
            System.out.println(t.getClass().getName() + " was deleted");
            return t;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("Delete " + t.getClass().getSimpleName() + ": " + t.toString() + " failed" + e.getMessage());
        }
    }

    public SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

}

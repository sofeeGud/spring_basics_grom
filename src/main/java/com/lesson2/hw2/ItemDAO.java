package com.lesson2.hw2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class ItemDAO {
    private SessionFactory sessionFactory;


    public Item save(Item item) throws Exception {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            item.setDateCreated(new Date());
            item.setLastUpdatedDate(new Date());
            session.save(item);

            session.getTransaction().commit();
            System.out.println(item.getClass().getName()+" saved with id:"+item.getId());
            return item;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            throw new Exception("Save "+item.getClass().getSimpleName()+": "+item.toString()+" failed"+e.getMessage());
        }
    }

    public Item update(Item item) throws Exception{
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            item.setLastUpdatedDate(new Date());
            session.update(item);

            session.getTransaction().commit();
            System.out.println(item.getClass().getName()+" updated");
            return item;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            throw new Exception("Update "+item.getClass().getSimpleName()+": "+item.toString()+" failed"+e.getMessage());
        }
    }

    public Item delete(Item item) throws Exception{
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            session.delete(item);

            session.getTransaction().commit();
            System.out.println(item.getClass().getName()+" id:"+item.getId()+" was deleted");
            return item;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            throw new Exception("Delete "+item.getClass().getSimpleName()+": "+item.toString()+" failed"+e.getMessage());
        }
    }

    public Item findById(Long id) throws Exception{
        try (Session session = createSessionFactory().openSession()) {

            return session.get(Item.class, id);

        } catch (HibernateException e) {
            throw new Exception(getClass().getSimpleName() + "-findById: " + id + " failed. " + e.getMessage());
        }
    }

    public Item findByName(String name) throws Exception{
        try (Session session = createSessionFactory().openSession()) {

            return (Item) session.createSQLQuery("SELECT * FROM ITEM WHERE NAME = :name")
                    .setParameter("name", name)
                    .addEntity(Item.class).getSingleResult();

        } catch (HibernateException e) {
            throw new Exception(getClass().getSimpleName()+"-findByName: "+name+" failed. "+e.getMessage());
        } catch (Exception e){
            return null;
        }
    }


    private SessionFactory createSessionFactory(){
        if(sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}

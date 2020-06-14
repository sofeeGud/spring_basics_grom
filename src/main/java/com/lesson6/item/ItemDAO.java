package com.lesson6.item;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
@Repository
public class ItemDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Item save(Item item) throws BadRequestException {
        try {
            entityManager.persist(item);
            return item;
        } catch (Exception e) {
            throw new BadRequestException("Save : " + item.getId() + " failed" + e.getMessage());
        }
    }

    public Item findById(Long id) throws BadRequestException {
        try {
            return entityManager.find(Item.class, id);
        } catch (Exception e) {
            throw new BadRequestException("Find : " + id + " failed" + e.getMessage());
        }
    }


    public Item update(Item item) throws BadRequestException {
        try {
            entityManager.merge(item);
            return item;
        } catch (Exception e) {
            throw new BadRequestException("Update : " + item.getId() + " failed" + e.getMessage());
        }
    }

    public void delete(Long id) throws BadRequestException {
        try {
            entityManager.remove(entityManager.find(Item.class, id));
        } catch (Exception e) {
            throw new BadRequestException("Delete : " + id + " failed" + e.getMessage());
        }
    }

    public int deleteByName(String name) {
        Query query = entityManager.createNativeQuery("DELETE FROM ITEMS\n" +
                "WHERE INSTR(NAME, ?) > 0");
        query.setParameter(1, name);
        return query.executeUpdate();
    }
}

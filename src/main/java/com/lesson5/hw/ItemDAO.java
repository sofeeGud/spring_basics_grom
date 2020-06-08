package com.lesson5.hw;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
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

    public Item findById(Long id) throws BadRequestException {
        try {
            return entityManager.find(Item.class, id);
        } catch (Exception e) {
            throw new BadRequestException("Find : " + id + " failed" + e.getMessage());
        }
    }
}

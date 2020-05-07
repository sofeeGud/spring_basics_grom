package com.lesson2.hw2;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {

    @Autowired
    ItemDAO itemDAO;

    public Item save(Item item) throws HibernateException, BadRequestException {
        validateItem(item);
        return itemDAO.save(item);
    }

    public Item update(Item item) throws HibernateException, BadRequestException {
        validateItem(item);
        return itemDAO.update(item);
    }

    public Item delete(Long id) throws HibernateException, BadRequestException {
        Item item = itemDAO.findById(id);
        if (item == null)
            throw new BadRequestException("There is no item with id: " + id);

        return itemDAO.delete(item);
    }

    public Item findById(Long id) throws HibernateException, BadRequestException {
        if (itemDAO.findById(id) == null)
            throw new BadRequestException("Not found");

        return itemDAO.findById(id);
    }

    private void validateItem(Item item) throws BadRequestException {
        if (item.getName().equals(""))
            throw new BadRequestException("Item name can not be empty");

        if (itemDAO.findByName(item.getName()) != null)
            throw new BadRequestException("Item with name: " + item.getName() + " already exists");

        if (findById(item.getId()) == null)
            throw new BadRequestException("Not found");

    }
}

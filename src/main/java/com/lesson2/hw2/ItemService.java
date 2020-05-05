package com.lesson2.hw2;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ItemService {

    @Autowired
    ItemDAO itemDAO;

    public Item save(Item item) throws HibernateException {
        validateItem(item);
        return itemDAO.save(item);
    }

    public Item update(Item item) throws HibernateException {
        validateItem(item);
        return itemDAO.update(item);
    }

    public Item delete(Long id) throws HibernateException {
        Item item = itemDAO.findById(id);
        if (item == null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "There is no item with id: " + id);

        return itemDAO.delete(item);
    }

    public Item findById(Long id) throws HibernateException {
        if (itemDAO.findById(id) == null)
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not found");

        return itemDAO.findById(id);
    }

    private void validateItem(Item item) {
        if (item.getName().equals(""))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Item name can not be empty");

        if (itemDAO.findByName(item.getName()) != null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Item with name: " + item.getName() + " already exists");

        if (findById(item.getId()) == null)
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not found");

    }
}

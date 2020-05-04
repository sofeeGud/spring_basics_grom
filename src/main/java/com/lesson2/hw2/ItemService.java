package com.lesson2.hw2;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {

    @Autowired
    ItemDAO itemDAO;
    public Item save(Item item) throws HttpExсeption, HibernateException  {
        validateItem(item);
        return itemDAO.save(item);
    }

    public Item update(Item item) throws HttpExсeption, HibernateException{
        validateItem(item);
        return itemDAO.update(item);
    }

    public Item delete(Long id) throws HttpExсeption, HibernateException  {
        Item item = itemDAO.findById(id);
        if(item == null)
            throw new HttpExсeption (404,"There is no item with id: "+id);

        return itemDAO.delete(item);
    }

    public Item findById(Long id) throws HttpExсeption, HibernateException {
        if (itemDAO.findById(id)==null)
            throw new HttpExсeption(404,"Not found");
        return itemDAO.findById(id);
    }

    private void validateItem(Item item) throws HttpExсeption {
        if(item.getName().equals(""))
            throw new HttpExсeption(400,"Item name can not be empty");
        if(itemDAO.findByName(item.getName()) != null)
            throw new HttpExсeption(400,"Item with name: "+item.getName()+" already exists");
        if (findById(item.getId())==null)
            throw new HttpExсeption(404,"Not found");

    }
}

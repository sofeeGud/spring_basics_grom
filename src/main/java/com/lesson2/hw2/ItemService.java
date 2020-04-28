package com.lesson2.hw2;

import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {

    @Autowired
    ItemDAO itemDAO;
    public Item save(Item item) throws Exception {
        validateItem(item);
        return itemDAO.save(item);
    }

    public Item update(Item item) throws Exception {
        validateItem(item);
        return itemDAO.update(item);
    }

    public Item delete(Long id) throws Exception {
        Item item = itemDAO.findById(id);
        if(item == null)
            throw new Exception("There is no item with id: "+id);

        return itemDAO.delete(item);
    }

    public Item findById(Long id) throws Exception{
        return itemDAO.findById(id);
    }

    private void validateItem(Item item) throws Exception {
        if(item.getName().equals(""))
            throw new HttpExсeption(400,"Item name can not be empty");
        if(itemDAO.findByName(item.getName()) != null)
            throw new HttpExсeption(400,"Item with name: "+item.getName()+" already exists");
    }
}

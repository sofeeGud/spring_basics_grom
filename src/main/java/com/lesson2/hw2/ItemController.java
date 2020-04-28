package com.lesson2.hw2;

import org.springframework.beans.factory.annotation.Autowired;

public class ItemController {
    @Autowired
    ItemService itemService;


    public Item save(Item item) throws Exception {
        return itemService.save(item);
    }

    public Item update(Item item) throws Exception{
        return itemService.update(item);
    }

    public Item delete(Long id) throws Exception{
        return itemService.delete(id);
    }

    public Item findById(Long id) throws Exception{
        return itemService.findById(id);
    }
}

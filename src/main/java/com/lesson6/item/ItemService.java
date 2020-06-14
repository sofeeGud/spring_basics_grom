package com.lesson6.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemService {
    private ItemDAO itemDAO;

    @Autowired
    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public Item save(Item item) throws BadRequestException {

        return itemDAO.save(item);
    }

    public Item update(Item item) throws BadRequestException {

        return itemDAO.update(item);
    }

    public Item findById(Long id) throws BadRequestException {

        return itemDAO.findById(id);
    }

    public void delete(Long id) throws Exception {

        itemDAO.delete(id);
    }

    public int deleteByName(String name) {

        return itemDAO.deleteByName(name);
    }
}

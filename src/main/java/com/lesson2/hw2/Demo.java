package com.lesson2.hw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller

public class Demo {
    @Autowired
    ItemController itemController;

    @RequestMapping(method = RequestMethod.GET, value = "/findById", produces = "text/plain")
    public @ResponseBody
    String findById(Long id) {
        try {
            return itemController.findById(id).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/plain")
    public @ResponseBody
    String save(Item item) {
        try {
            itemController.save(item);
            return item.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text/plain")
    public @ResponseBody
    String update(Item item) {
        try {
            itemController.update(item);
            return item.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    public @ResponseBody
    String delete(Item item) {
        try {
            itemController.delete(item.getId());
            return item.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

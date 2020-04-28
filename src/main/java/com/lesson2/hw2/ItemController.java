package com.lesson2.hw2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;


    @RequestMapping(method = RequestMethod.GET, value = "/findById/{id}", produces = "text/plain")
    public @ResponseBody
    String findById(@PathVariable String  id) {
        try {
            return itemService.findById(Long.parseLong(id)).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/json")
    public @ResponseBody
    String save(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Item item = mapper.readValue(json, Item.class);
          return itemService.save(item).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text/plain")
    public @ResponseBody
    String update(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Item item = mapper.readValue(json, Item.class);
            return  itemService.update(item).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    public @ResponseBody
    String delete(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Item item = mapper.readValue(json, Item.class);
            return  itemService.delete(item.getId()).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

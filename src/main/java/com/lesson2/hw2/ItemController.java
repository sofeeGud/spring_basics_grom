package com.lesson2.hw2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;


    @RequestMapping(method = RequestMethod.GET, value = "/findById/{id}", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> findById(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(itemService.findById(Long.parseLong(id)).toString());
        } catch (HttpExсeption e) {
            return ResponseEntity.badRequest()
                    .body(e.getStatusCode() + " " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/json")
    public @ResponseBody
    ResponseEntity<String> save(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Item item = mapper.readValue(json, Item.class);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(itemService.save(item).toString());
        } catch (HttpExсeption e) {
            return ResponseEntity.badRequest()
                    .body(e.getStatusCode() + " " + e.getMessage());
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(
                    "Error read", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> update(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Item item = mapper.readValue(json, Item.class);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(itemService.update(item).toString());
        } catch (HttpExсeption e) {
            return ResponseEntity.badRequest()
                    .body(e.getStatusCode() + " " + e.getMessage());
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(
                    "Error read", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> delete(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Item item = mapper.readValue(json, Item.class);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(itemService.delete(item.getId()).toString());
        } catch (HttpExсeption e) {
            return ResponseEntity.badRequest()
                    .body(e.getStatusCode() + " " + e.getMessage());
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(
                    "Error read", HttpStatus.BAD_REQUEST);
        }
    }
}

package com.lesson3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesson3.BadRequestException;
import com.lesson3.model.Storage;
import com.lesson3.service.StorageService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class StorageController {
    private StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveStorage")
    public ResponseEntity<String> save(@RequestBody String json) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            Storage storage = mapper.readValue(json, Storage.class);
            return new ResponseEntity<>(storageService.save(storage).toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findStorage/{id}")
    public ResponseEntity<String> findById(@PathVariable String id) {
        try {

            Storage storage = storageService.findById(Long.parseLong(id));

            return new ResponseEntity<>(storage.toString(), HttpStatus.OK);
        } catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateStorage")
    public ResponseEntity<String> update(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Storage storage = mapper.readValue(json, Storage.class);
            return new ResponseEntity<>(storageService.update(storage).toString(), HttpStatus.OK);

        } catch (BadRequestException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteStorage/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {

        try {

            storageService.delete(Long.parseLong(id));
            return new ResponseEntity<>("Deleted ok", HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.lesson3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesson3.BadRequestException;
import com.lesson3.model.File;
import com.lesson3.model.Storage;
import com.lesson3.service.Service;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controller3 {
    private Service service;

    @Autowired
    public Controller3(Service service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/put", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> put(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Storage storage = mapper.readValue(json, Storage.class);
            File file = mapper.readValue(json, File.class);
            Session session = mapper.readValue(json, Session.class);
            return new ResponseEntity<>(service.put(storage, file, session).toString(), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> delete(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Storage storage = mapper.readValue(json, Storage.class);
            File file = mapper.readValue(json, File.class);
            Session session = mapper.readValue(json, Session.class);
            return new ResponseEntity<>(service.delete(storage, file, session).toString(), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    @RequestMapping(method = RequestMethod.PUT, value = "/transferAll", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> transferAll(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Storage storageTo = mapper.readValue(json, Storage.class);
            Storage storageFrom = mapper.readValue(json, Storage.class);
            return new ResponseEntity<>(service.transferAll(storageFrom, storageTo), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/transferFile", produces = "text/plain")
    public @ResponseBody
    ResponseEntity<String> transferFile(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Storage storageTo = mapper.readValue(json, Storage.class);
            Storage storageFrom = mapper.readValue(json, Storage.class);
            Long id = mapper.readValue(json, Long.class);
            Session session = mapper.readValue(json, Session.class);
            return new ResponseEntity(service.transferFile(storageFrom, storageTo, id, session), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

}

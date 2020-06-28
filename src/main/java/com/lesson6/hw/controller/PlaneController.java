package com.lesson6.hw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesson6.hw.BadRequestException;
import com.lesson6.hw.dao.PlaneDAO;
import com.lesson6.hw.model.Plane;
import com.lesson6.hw.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/plane")
public class PlaneController extends GeneralController<Plane> {
    private PlaneDAO planeDAO;
    private PlaneService planeService;

    @Autowired
    public PlaneController(PlaneDAO planeDAO, PlaneService planeService) {
        this.planeDAO = planeDAO;
        this.planeService = planeService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/plain")
    public ResponseEntity<String> save(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Plane plane = mapper.readValue(json, Plane.class);
            planeService.save(plane);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "update", produces = "text/plain")
    public ResponseEntity<String> update(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Plane plane = mapper.readValue(json, Plane.class);
            planeService.update(plane);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete/{id}", produces = "text/plain")
    public ResponseEntity<String> delete(@PathVariable String id) {
        try {
            planeDAO.delete(Long.parseLong(id));
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOldPlanes", produces = "text/plain")
    public ResponseEntity<String> oldPlanes() {
        try {
            return new ResponseEntity<>(parseObjectList(planeDAO.oldPlanes()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRegularPlanes", produces = "text/plain")
    public ResponseEntity<String> regularPlanes() {
        try {
            return new ResponseEntity<>(parseObjectList(planeDAO.regularPlanes()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.lesson6.hw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesson6.hw.dao.FlightDAO;
import com.lesson6.hw.BadRequestException;
import com.lesson6.hw.model.Filter;
import com.lesson6.hw.model.Flight;
import com.lesson6.hw.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/flight")
public class FlightController extends GeneralController<Flight> {

    private FlightDAO flightDAO;
    private FlightService flightService;

    @Autowired
    public FlightController(FlightDAO flightDAO, FlightService flightService) {
        this.flightDAO = flightDAO;
        this.flightService = flightService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/plain")
    public ResponseEntity<String> save(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Flight flight = mapper.readValue(json, Flight.class);
            flightService.save(flight);
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
            Flight flight = mapper.readValue(json, Flight.class);
            flightService.update(flight);
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
            flightService.delete(Long.parseLong(id));
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/mostPopularFrom")
    public ResponseEntity<String> mostPopularFrom() {
        try {
            return new ResponseEntity<>(parseObjectList(flightDAO.mostPopularTo()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mostPopularTo")
    public ResponseEntity<String> mostPopularTo() {
        try {
            return new ResponseEntity<>(parseObjectList(flightDAO.mostPopularTo()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/findByFilter")
    public ResponseEntity<String> flightsByDate(@RequestBody Filter filter) {
        try {
            return new ResponseEntity<>(parseObjectList(flightDAO.flightsByDate(filter)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

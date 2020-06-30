package com.lesson6.hw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesson6.hw.BadRequestException;
import com.lesson6.hw.dao.PassengerDAO;
import com.lesson6.hw.service.PassengerService;
import com.lesson6.hw.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/passenger")
public class PassengerController extends GeneralController<Passenger> {
    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/plain")
    public ResponseEntity<String> save(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Passenger passenger = mapper.readValue(json, Passenger.class);
            passengerService.save(passenger);
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
            Passenger passenger = mapper.readValue(json, Passenger.class);
            passengerService.update(passenger);
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
            passengerService.delete(Long.parseLong(id));
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRegularPassengers", produces = "text/plain")
    public ResponseEntity<String> regularPassengers() {
        try {
            return new ResponseEntity<>(parseObjectList(passengerService.regularPassengers()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

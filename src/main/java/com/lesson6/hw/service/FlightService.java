package com.lesson6.hw.service;

import com.lesson6.hw.BadRequestException;
import com.lesson6.hw.dao.FlightDAO;
import com.lesson6.hw.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    private FlightDAO flightDAO;

    @Autowired
    public FlightService(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }


    public Flight save(Flight flight) throws BadRequestException {
        passengerValidator(flight);
        return flightDAO.save(flight);
    }

    public Flight update(Flight flight) throws BadRequestException {
        passengerValidator(flight);
        return flightDAO.update(flight);
    }

    public void delete(Long id) throws BadRequestException {
        Flight flight = flightDAO.findById(id);
        passengerValidator(flight);
        flightDAO.delete(id);
    }

    private void passengerValidator(Flight flight) throws BadRequestException {

        if (flight.getPlane().getId() == null)
            throw new BadRequestException("Flight plane must be chosen");
        if (flight.getDateFlight() == null)
            throw new BadRequestException("Flight date must be chosen");
        if (flight.getCityFrom().equals(""))
            throw new BadRequestException("Flight city from can not be empty");
        if (flight.getCityTo().equals(""))
            throw new BadRequestException("Flight city to can not be empty");
    }
}

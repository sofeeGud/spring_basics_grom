package com.lesson6.hw.service;

import com.lesson6.hw.BadRequestException;
import com.lesson6.hw.dao.PassengerDAO;
import com.lesson6.hw.model.Flight;
import com.lesson6.hw.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
@Service
public class PassengerService {
    private PassengerDAO passengerDAO;

    @Autowired
    public PassengerService(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    public Passenger save(Passenger passenger) throws BadRequestException {
        passengerValidator(passenger);
        return passengerDAO.save(passenger);
    }

    public Passenger update(Passenger passenger) throws BadRequestException {
        passengerValidator(passenger);
        return passengerDAO.update(passenger);
    }

    public void delete(Long id) throws BadRequestException {
        passengerDAO.findById(id);
        passengerValidator(passengerDAO.findById(id));
    }

    public Collection<Passenger> regularPassengers(){
        return passengerDAO.regularPassengers();
    }

    private void passengerValidator(Passenger passenger) throws BadRequestException {
        if (passenger.getLastName().equals(""))
            throw new BadRequestException("Passenger last name can not be empty");
        if (passenger.getPassportCode().equals(""))
            throw new BadRequestException("Passenger passportCode can not be empty");
    }


}

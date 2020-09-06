package com.lesson6.hw.dao;

import com.lesson6.hw.model.Passenger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PassengerDAO extends GeneralDAOImpl<Passenger> {
    private static final String SQL_REGULAR_PASSENGERS = "SELECT * " +
            "FROM PASSENGER " +
            "WHERE EXISTS ( " +
            "    SELECT  * " +
            "    FROM    FLIGHT_PASSENGER FP " +
            "       JOIN FLIGHT F ON FP.FLIGHT_ID = F.FLIGHT_ID " +
            "    WHERE FP.PASSENGER_ID = PASSENGER.PASSENGER_ID " +
            "    GROUP BY FP.PASSENGER_ID, EXTRACT(YEAR FROM F.DATE_FLIGHT) " +
            "    HAVING COUNT(DISTINCT F.FLIGHT_ID) >= 25" +
            ")";

    public List<Passenger> regularPassengers() {
        return (List<Passenger>) entityManager.createNativeQuery(SQL_REGULAR_PASSENGERS, Passenger.class).getResultList();
    }
}

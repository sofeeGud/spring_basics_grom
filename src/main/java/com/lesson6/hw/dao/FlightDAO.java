package com.lesson6.hw.dao;

import com.lesson6.hw.model.Plane;
import com.lesson6.hw.model.Filter;
import com.lesson6.hw.model.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

@Repository
public class FlightDAO extends GeneralDAOImpl<Flight> {

    @PersistenceContext
    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;

    public Collection<Flight> mostPopularTo() {
        return (List<Flight>) entityManager.createNativeQuery("SELECT FLIGHT.*\n" +
                "FROM FLIGHT\n" +
                "LEFT JOIN (\n" +
                "    SELECT CITY_TO, COUNT(FLIGHT_ID) rnk\n" +
                "    FROM FLIGHT\n" +
                "    GROUP BY CITY_TO\n" +
                ") city_rnk ON FLIGHT.CITY_TO = city_rnk.CITY_TO\n" +
                "ORDER BY city_rnk.rnk DESC", Flight.class)
                .setMaxResults(10)
                .getResultList();
    }

    public Collection<Flight> mostPopularFrom() {
        return (List<Flight>) entityManager.createNativeQuery("SELECT FLIGHT.*\n" +
                "FROM FLIGHT\n" +
                "LEFT JOIN (\n" +
                "    SELECT CITY_FROM, COUNT(FLIGHT_ID) rnk\n" +
                "    FROM FLIGHT\n" +
                "    GROUP BY CITY_FROM\n" +
                ") city_rnk ON FLIGHT.CITY_FROM = city_rnk.CITY_FROM\n" +
                "ORDER BY city_rnk.rnk DESC", Flight.class)
                .setMaxResults(10)
                .getResultList();
    }

    public Collection<Flight> flightsByDate(Filter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> criteriaQuery = cb.createQuery(Flight.class);
        Root<Flight> flightRoot = criteriaQuery.from(Flight.class);
        Join<Flight, Plane> planes = flightRoot.join("plane");

        criteriaQuery.select(flightRoot);

        Predicate criteria = cb.conjunction();
        //CityFrom
        if (filter.getCityFrom() != null)
            criteria = cb.and(criteria, cb.equal(flightRoot.get("cityFrom"), filter.getCityFrom()));
        //CityTo
        if (filter.getCityTo() != null)
            criteria = cb.and(criteria, cb.equal(flightRoot.get("cityTo"), filter.getCityTo()));
        //Dates
        if (filter.getDateFrom() != null && filter.getDateTo() != null)
            criteria = cb.and(criteria, cb.between(flightRoot.get("dateFlight"), filter.getDateFrom(), filter.getDateTo()));
        //Plane
        if (filter.getPlaneModel() != null)
            criteria = cb.and(criteria, cb.equal(planes.get("model"), filter.getPlaneModel()));

        criteriaQuery.where(criteria);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}

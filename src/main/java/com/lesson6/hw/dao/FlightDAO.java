package com.lesson6.hw.dao;

import com.lesson6.hw.model.Plane;
import com.lesson6.hw.model.Filter;
import com.lesson6.hw.model.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class FlightDAO extends GeneralDAOImpl<Flight> {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String SQL_MOST_POPULAR_TO ="SELECT FLIGHT.* " +
            "FROM FLIGHT " +
            "LEFT JOIN ( " +
            "    SELECT CITY_TO, COUNT(FLIGHT_ID) RNK " +
            "    FROM FLIGHT " +
            "    GROUP BY CITY_TO " +
            ") CITY_RNK ON FLIGHT.CITY_TO = CITY_RNK.CITY_TO " +
            "ORDER BY CITY_RNK.RNK DESC";

    private static final String SQL_MOST_POPULAR_FROM ="SELECT FLIGHT.* " +
            "FROM FLIGHT " +
            "LEFT JOIN ( " +
            "    SELECT CITY_FROM, COUNT(FLIGHT_ID) RNK " +
            "    FROM FLIGHT " +
            "    GROUP BY CITY_FROM " +
            ") CITY_RNK ON FLIGHT.CITY_FROM = CITY_RNK.CITY_FROM " +
            "ORDER BY CITY_RNK.RNK DESC";

    private static final String SQL_FLIGHTS_BY_DATE ="SELECT F.* " +
            "FROM FLIGHT F " +
            "LEFT JOIN PLANE P ON P.ID=F.PLANE_ID " +
            "WHERE F.CITY_FROM= :city_from " +
            "AND F.CITY_TO= :city_to " +
            "AND F.DATE_FLIGHT BETWEEN :date_from AND :date_to " +
            "AND P.MODEL= :model " +
            "ORDER BY F.DATE_FLIGHT ";

    public List<Flight> mostPopularTo() {
        return (List<Flight>) entityManager.createNativeQuery(SQL_MOST_POPULAR_TO, Flight.class)
                .setMaxResults(10)
                .getResultList();
    }

    public List<Flight> mostPopularFrom() {
        return (List<Flight>) entityManager.createNativeQuery(SQL_MOST_POPULAR_FROM, Flight.class)
                .setMaxResults(10)
                .getResultList();
    }

    public List<Flight> flightsByDate(Filter filter) {
       /* CriteriaBuilder cb = entityManager.getCriteriaBuilder();
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
        return entityManager.createQuery(criteriaQuery).getResultList();*/

        Query query= entityManager.createNativeQuery(SQL_FLIGHTS_BY_DATE, Flight.class);
        query.setParameter("city_from", filter.getCityFrom());
        query.setParameter("city_to", filter.getCityTo());
        query.setParameter("date_from", filter.getDateFrom());
        query.setParameter("date_to", filter.getDateTo());
        query.setParameter("model", filter.getPlaneModel());
        return query.getResultList();
    }

}

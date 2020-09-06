package com.lesson6.hw.dao;

import com.lesson6.hw.model.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PlaneDAO extends GeneralDAOImpl<Plane> {

    private static final String SQL_OLD_PLANES = "SELECT * FROM PLANE WHERE EXTRACT(YEAR FROM CURRENT_DATE)-YEAR_PRODUCED >= 20";
    private static final String SQL_REGULAR_PLANES = "SELECT * " +
            "FROM PLANE " +
            "WHERE EXISTS( " +
            "    SELECT * " +
            "    FROM FLIGHT " +
            "    WHERE FLIGHT.PLANE_ID = PLANE.PLANE_ID " +
            "    GROUP BY FLIGHT.PLANE_ID, EXTRACT(YEAR FROM FLIGHT.DATE_FLIGHT) " +
            "    HAVING COUNT(FLIGHT.FLIGHT_ID) >= 300 " +
            ")";

    public List<Plane> oldPlanes() {
        return (List<Plane>) entityManager.createNativeQuery(SQL_OLD_PLANES, Plane.class).getResultList();
    }

    public List<Plane> regularPlanes() {
        return (List<Plane>) entityManager.createNativeQuery(SQL_REGULAR_PLANES, Plane.class).getResultList();
    }
}

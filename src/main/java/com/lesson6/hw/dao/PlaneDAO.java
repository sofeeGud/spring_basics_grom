package com.lesson6.hw.dao;

import com.lesson6.hw.model.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class PlaneDAO extends GeneralDAOImpl<Plane> {

    private static final String SQL_OLD_PLANES = "SELECT * FROM PLANE WHERE EXTRACT(YEAR FROM current_date)-YEAR_PRODUCED >= 20";
    private static final String SQL_REGULAR_PLANES = "SELECT *\n" +
            "FROM PLANE\n" +
            "WHERE EXISTS(\n" +
            "    SELECT *\n" +
            "    FROM FLIGHT\n" +
            "    WHERE FLIGHT.PLANE_ID = PLANE.PLANE_ID\n" +
            "    GROUP BY FLIGHT.PLANE_ID, EXTRACT(YEAR FROM FLIGHT.DATE_FLIGHT)\n" +
            "    HAVING COUNT(FLIGHT.FLIGHT_ID) >= 300\n" +
            ")";

    @PersistenceContext
    private EntityManager entityManager;


    public Collection<Plane> oldPlanes() {
        return null;
    }

    public Collection<Plane> regularPlanes() {
        return null;
    }
}

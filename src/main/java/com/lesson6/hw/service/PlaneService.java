package com.lesson6.hw.service;

import com.lesson6.hw.dao.PlaneDAO;
import com.lesson6.hw.BadRequestException;
import com.lesson6.hw.model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
@Service
public class PlaneService {
    private PlaneDAO planeDAO;

    @Autowired
    public PlaneService(PlaneDAO planeDAO) {
        this.planeDAO = planeDAO;
    }

    public Plane save(Plane plane) throws BadRequestException {
        planeValidator(plane);
        return planeDAO.save(plane);
    }

    public Plane update(Plane plane) throws BadRequestException {
        planeValidator(plane);
        return planeDAO.update(plane);
    }

    public void delete(Long id) throws BadRequestException {
        Plane plane = planeDAO.findById(id);
        planeValidator(plane);
        planeDAO.delete(id);
    }

    private void planeValidator(Plane plane) throws BadRequestException {
        if (plane.getModel().equals(""))
            throw new BadRequestException("Plane model can not be empty");
        if (plane.getCode().equals(""))
            throw new BadRequestException("Plane code can not be empty");
    }

    public Collection<Plane> oldPlanes() {
        return planeDAO.oldPlanes();
    }

    public Collection<Plane> regularPlanes() {
        return planeDAO.regularPlanes();
    }
}

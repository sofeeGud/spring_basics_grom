package com.lesson6.hw.service;

import com.lesson6.hw.dao.PlaneDAO;
import com.lesson6.hw.BadRequestException;
import com.lesson6.hw.model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
@Service
public class PlaneService {
    private PlaneDAO planeDAO;

    @Autowired
    public PlaneService(PlaneDAO planeDAO) {
        this.planeDAO = planeDAO;
    }

    public Plane save(Plane plane) throws Exception {
        planeValidator(plane);
        return planeDAO.save(plane);
    }

    public Plane update(Plane plane) throws Exception {
        planeValidator(plane);
        return planeDAO.update(plane);
    }

    public void delete(Long id) throws Exception {
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

    public List<Plane> oldPlanes() {
        return planeDAO.oldPlanes();
    }

    public List<Plane> regularPlanes() {
        return planeDAO.regularPlanes();
    }
}

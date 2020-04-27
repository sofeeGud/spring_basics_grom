package com.lesson2.hw1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    Route route;
    @Autowired
    Service service;
    @Autowired
    Step step;

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = "text/plain")
    public @ResponseBody
    String callByBean() {
        return
                route.getId() +
                        route.getSteps() +
                        step.getId() +
                        step.getParamsServiceFrom() +
                        step.getParamsServiceTo() +
                        step.getServiceFrom() +
                        step.getServiceTo() +
                        service.getId() +
                        service.getName() +
                        service.getParamsToCall();
    }
}

package com.lesson2.hw1;

import java.util.Map;

public class Step {
    private long id;
    Service serviceFrom;
    Service serviceTo;
    Map paramsServiceFrom;
    Map paramsServiceTo;

    public Step(long id, Service serviceFrom, Service serviceTo, Map paramsServiceFrom, Map paramsServiceTo) {
        this.id = id;
        this.serviceFrom = serviceFrom;
        this.serviceTo = serviceTo;
        this.paramsServiceFrom = paramsServiceFrom;
        this.paramsServiceTo = paramsServiceTo;
    }

    public long getId() {
        return id;
    }

    public Service getServiceFrom() {
        return serviceFrom;
    }

    public Service getServiceTo() {
        return serviceTo;
    }

    public Map getParamsServiceFrom() {
        return paramsServiceFrom;
    }

    public Map getParamsServiceTo() {
        return paramsServiceTo;
    }
}

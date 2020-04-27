package com.lesson2.hw1;

import java.util.List;

public class Service {
    private long id;
    private String name;
    List paramsToCall;

    public Service(long id, String name, List paramsToCall) {
        this.id = id;
        this.name = name;
        this.paramsToCall = paramsToCall;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List getParamsToCall() {
        return paramsToCall;
    }
}

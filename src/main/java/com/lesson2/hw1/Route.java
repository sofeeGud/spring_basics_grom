package com.lesson2.hw1;

import java.util.List;

public class Route {
    private String id;
    private List<Step> steps;

    public Route(String id, List<Step> steps) {
        this.id = id;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public List<Step> getSteps() {
        return steps;
    }
}

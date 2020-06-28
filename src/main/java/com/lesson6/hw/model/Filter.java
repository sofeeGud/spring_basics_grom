package com.lesson6.hw.model;

import java.util.Calendar;
import java.util.Date;

public class Filter {
    private Date DateFrom;
    private Date DateTo;
    private String cityFrom;
    private String cityTo;
    private String planeModel;

    public void setDate(Date date) {
        Calendar dt = Calendar.getInstance();
        dt.setTime(date);
        dt.add(Calendar.DATE, -1);
        DateFrom = dt.getTime();
        dt.setTime(date);
        dt.add(Calendar.DATE, 1);
        DateTo = dt.getTime();
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(String planeModel) {
        this.planeModel = planeModel;
    }
}

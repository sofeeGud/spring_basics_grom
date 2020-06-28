package com.lesson6.hw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "FLIGHT")
public class Flight {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("plane")
    private Plane plane;
    @JsonProperty("passengers")
    private List<Passenger> passengers = new ArrayList<>();
    @JsonProperty("dateFlight")
    private Date dateFlight;
    @JsonProperty("cityFrom")
    private String cityFrom;
    @JsonProperty("cityTo")
    private String cityTo;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "F_SEQ", sequenceName = "FLIGHT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "F_SEQ")
    public Long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PLANE_ID")
    public Plane getPlane() {
        return plane;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "PASSENGER_FLIGHT",
            joinColumns = { @JoinColumn(name = "FLIGHT_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "PASS_ID", nullable = false, updatable = false) })
    @JsonIgnore
    public List<Passenger> getPassenger() {
        return passengers;
    }

    @Column(name = "DATE_FLIGHT")
    public Date getDateFlight() {
        return dateFlight;
    }

    @Column(name = "CITY_FROM")
    public String getCityFrom() {
        return cityFrom;
    }

    @Column(name = "CITY_TO")
    public String getCityTo() {
        return cityTo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setPassenger(List<Passenger> passenger) {
        this.passengers = passenger;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", plane=" + plane +
                ", passengers=" + passengers +
                ", dateFlight=" + dateFlight +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (id != null ? !id.equals(flight.id) : flight.id != null) return false;
        if (plane != null ? !plane.equals(flight.plane) : flight.plane != null) return false;
        if (passengers != null ? !passengers.equals(flight.passengers) : flight.passengers != null) return false;
        if (dateFlight != null ? !dateFlight.equals(flight.dateFlight) : flight.dateFlight != null) return false;
        if (cityFrom != null ? !cityFrom.equals(flight.cityFrom) : flight.cityFrom != null) return false;
        return cityTo != null ? cityTo.equals(flight.cityTo) : flight.cityTo == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (plane != null ? plane.hashCode() : 0);
        result = 31 * result + (passengers != null ? passengers.hashCode() : 0);
        result = 31 * result + (dateFlight != null ? dateFlight.hashCode() : 0);
        result = 31 * result + (cityFrom != null ? cityFrom.hashCode() : 0);
        result = 31 * result + (cityTo != null ? cityTo.hashCode() : 0);
        return result;
    }
}
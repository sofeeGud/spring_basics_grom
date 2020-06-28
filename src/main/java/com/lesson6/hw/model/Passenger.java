package com.lesson6.hw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "PASSENGER")
public class Passenger {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("nationality")
    private String nationality;
    @JsonProperty("dateOfBirth")
    private Date dateOfBirth;
    @JsonProperty("passportCode")
    private String passportCode;
    @JsonProperty("flights")
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "passengers")
    private List<Flight> flights = new ArrayList<>();

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "PASS_SEQ", sequenceName = "PASSENGER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PASS_SEQ")
    public Long getId() {
        return id;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "NATIONALITY")
    public String getNationality() {
        return nationality;
    }

    @Column(name = "DATE_OF_BIRTH")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Column(name = "PASSPORT_CODE")
    public String getPassportCode() {
        return passportCode;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "PASSENGER_FLIGHT",
            joinColumns = { @JoinColumn(name = "PASS_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "FLIGHT_ID", nullable = false, updatable = false)})
    @JsonIgnore
    public List<Flight> getFlights() {
        return flights;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        if (!id.equals(passenger.id)) return false;
        if (!lastName.equals(passenger.lastName)) return false;
        if (!nationality.equals(passenger.nationality)) return false;
        if (!dateOfBirth.equals(passenger.dateOfBirth)) return false;
        if (!passportCode.equals(passenger.passportCode)) return false;
        return flights.equals(passenger.flights);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + nationality.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + passportCode.hashCode();
        result = 31 * result + flights.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passportCode='" + passportCode + '\'' +
                ", flights=" + flights +
                '}';
    }
}



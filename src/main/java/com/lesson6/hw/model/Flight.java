package com.lesson6.hw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "FLIGHT")
public class Flight {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "F_SEQ", sequenceName = "FLIGHT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "F_SEQ")
    @JsonProperty("id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PLANE_ID")
    @JsonProperty("plane")
    private Plane plane;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "PASSENGER_FLIGHT",
            joinColumns = { @JoinColumn(name = "FLIGHT_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "PASS_ID", nullable = false, updatable = false) })
    @JsonIgnore
    @JsonProperty("passengers")
    private List<Passenger> passengers = new ArrayList<>();
    @Column(name = "DATE_FLIGHT")
    @JsonProperty("dateFlight")
    private Date dateFlight;
    @Column(name = "CITY_FROM")
    @JsonProperty("cityFrom")
    private String cityFrom;
    @Column(name = "CITY_TO")
    @JsonProperty("cityTo")
    private String cityTo;

}
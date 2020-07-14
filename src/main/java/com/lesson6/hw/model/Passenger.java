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
@Table(name = "PASSENGER")
public class Passenger {

    @JsonProperty("id")
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "PASS_SEQ", sequenceName = "PASSENGER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PASS_SEQ")
    private Long id;
    @JsonProperty("lastName")
    @Column(name = "LAST_NAME")
    private String lastName;
    @JsonProperty("nationality")
    @Column(name = "NATIONALITY")
    private String nationality;
    @JsonProperty("dateOfBirth")
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;
    @JsonProperty("passportCode")
    @Column(name = "PASSPORT_CODE")
    private String passportCode;
    @JsonProperty("flights")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "PASSENGER_FLIGHT",
            joinColumns = { @JoinColumn(name = "PASS_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "FLIGHT_ID", nullable = false, updatable = false)})
    @JsonIgnore
    private List<Flight> flights = new ArrayList<>();

}



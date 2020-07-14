package com.lesson6.hw.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "PLANE")
public class Plane {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "P_SEQ", sequenceName = "PLANE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "P_SEQ")
    @JsonProperty("id")
    private Long id;
    @Column(name = "MODEL")
    @JsonProperty("model")
    private String model;
    @Column(name = "CODE")
    @JsonProperty("code")
    private String code;
    @Column(name = "YEAR_PRODUCED")
    @JsonProperty("yearProduced")
    private Date yearProduced;
    @Column(name = "AVG_FUEL_CONSUMPTION")
    @JsonProperty("avgFuelConsumption")
    private Double avgFuelConsumption;
}

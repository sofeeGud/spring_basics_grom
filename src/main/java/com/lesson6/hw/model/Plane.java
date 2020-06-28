package com.lesson6.hw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "PLANE")
public class Plane {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("model")
    private String model;
    @JsonProperty("code")
    private String code;
    @JsonProperty("yearProduced")
    private Date yearProduced;
    @JsonProperty("avgFuelConsumption")
    private Double avgFuelConsumption;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "P_SEQ", sequenceName = "PLANE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "P_SEQ")
    public Long getId() {
        return id;
    }

    @Column(name = "MODEL")
    public String getModel() {
        return model;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    @Column(name = "YEAR_PRODUCED")
    public Date getYearProduced() {
        return yearProduced;
    }

    @Column(name = "AVG_FUEL_CONSUMPTION")
    public Double getAvgFuelConsumption() {
        return avgFuelConsumption;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setYearProduced(Date yearProduced) {
        this.yearProduced = yearProduced;
    }

    public void setAvgFuelConsumption(Double avgFuelConsumption) {
        this.avgFuelConsumption = avgFuelConsumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (!id.equals(plane.id)) return false;
        if (!model.equals(plane.model)) return false;
        if (!code.equals(plane.code)) return false;
        if (!yearProduced.equals(plane.yearProduced)) return false;
        return avgFuelConsumption.equals(plane.avgFuelConsumption);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + yearProduced.hashCode();
        result = 31 * result + avgFuelConsumption.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", code='" + code + '\'' +
                ", yearProduced=" + yearProduced +
                ", avgFuelConsumption=" + avgFuelConsumption +
                '}';
    }
}

package com.lesson3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "STORAGE")
public class Storage {
    @JsonProperty("id")
    @Id
    @SequenceGenerator(name = "STORAGE_ID_SEQ", sequenceName = "STORAGE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORAGE_ID_SEQ")
    @Column(name = "ID")
    private long id;
    @JsonProperty("formatsSupported")
    @Column(name = "FORMATS_SUPPORTED")
    private String formatsSupported;
    @JsonProperty("storageCountry")
    @Column(name = "STORAGE_COUNTRY")
    private String storageCountry;
    @JsonProperty("storageSize")
    @Column(name = "STORAGE_SIZE")
    private long storageSize;

}

package com.lesson3.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormatsSupported() {
        return formatsSupported;
    }

    public void setFormatsSupported(String formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    public long getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }
}

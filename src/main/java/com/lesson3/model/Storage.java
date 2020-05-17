package com.lesson3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "STORAGE")
public class Storage {
    @JsonProperty("id")
    private long id;
    @JsonProperty("formatsSupported")
    private String formatsSupported;
    @JsonProperty("storageCountry")
    private String storageCountry;
    @JsonProperty("storageSize")
    private long storageSize;

    @Id
    @SequenceGenerator(name = "STORAGE_ID_SEQ", sequenceName = "STORAGE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORAGE_ID_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "FORMATS_SUPPORTED")
    public String getFormatsSupported() {
        return formatsSupported;
    }

    public void setFormatsSupported(String formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    @Column(name = "STORAGE_COUNTRY")
    public String getStorageCountry() {
        return storageCountry;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    @Column(name = "STORAGE_SIZE")
    public long getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", formatsSupported=" + formatsSupported +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageSize=" + storageSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storage storage = (Storage) o;

        if (id != storage.id) return false;
        if (storageSize != storage.storageSize) return false;
        if (!formatsSupported.equals(storage.formatsSupported)) return false;
        return storageCountry.equals(storage.storageCountry);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + formatsSupported.hashCode();
        result = 31 * result + storageCountry.hashCode();
        result = 31 * result + (int) (storageSize ^ (storageSize >>> 32));
        return result;
    }
}

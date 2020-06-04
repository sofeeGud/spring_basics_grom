package com.lesson3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@ToString
@EqualsAndHashCode
@Entity
@Table(name = "FILES")
public class File {
    @JsonProperty("id")
    @Id
    @SequenceGenerator(name = "FILE_ID_SEQ", sequenceName = "FILE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_ID_SEQ")
    @Column(name = "ID")
    private long id;
    @JsonProperty("name")
    @Column(name = "NAME")
    private String name;
    @JsonProperty("format")
    @Column(name = "FORMAT")
    private String format;
    @JsonProperty("size")
    @Column(name = "FILE_SIZE")
    private long size;

    @JsonProperty("storage")
    @ManyToOne
    @JoinColumn(name="STORAGE_ID", nullable = false)
    Storage storage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}

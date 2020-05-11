package com.lesson3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "FILE")
public class File {
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("format")
    private String format;
    @JsonProperty("size")
    private long size;
    @JsonProperty("storage")
    Storage storage;

    @Id
    @SequenceGenerator(name = "FILE_ID_SEQ", sequenceName = "FILE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_ID_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "FORMAT")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Column(name = "FILE_SIZE")
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Column(name = "STORAGE_ID")
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                ", storage=" + storage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (id != file.id) return false;
        if (size != file.size) return false;
        if (!name.equals(file.name)) return false;
        if (!format.equals(file.format)) return false;
        return storage.equals(file.storage);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + format.hashCode();
        result = 31 * result + (int) (size ^ (size >>> 32));
        result = 31 * result + storage.hashCode();
        return result;
    }
}

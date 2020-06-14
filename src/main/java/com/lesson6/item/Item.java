package com.lesson6.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

    @Entity
    @Table(name = "ITEM")
    public class Item {
        @JsonProperty("id")
        private long id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("dateCreated")
        private Date dateCreated;
        @JsonProperty("lastUpdatedDate")
        private Date lastUpdatedDate;
        @JsonProperty("description")
        private String description;

        @Id
        @SequenceGenerator(name = "ITEM_SEQ", sequenceName = "ITEM_SEQ", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ")
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

        @Column(name = "DATE_CREATED")
        public Date getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(Date dateCreated) {
            this.dateCreated = dateCreated;
        }

        @Column(name = "DATE_LAST_UPD")
        public Date getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(Date lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        @Column(name = "DESCRIPTION")
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", dateCreated=" + dateCreated +
                    ", lastUpdatedDate=" + lastUpdatedDate +
                    ", description='" + description + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Item item = (Item) o;

            if (id != item.id) return false;
            if (!name.equals(item.name)) return false;
            if (!dateCreated.equals(item.dateCreated)) return false;
            if (!lastUpdatedDate.equals(item.lastUpdatedDate)) return false;
            return description.equals(item.description);
        }

        @Override
        public int hashCode() {
            int result = (int) (id ^ (id >>> 32));
            result = 31 * result + name.hashCode();
            result = 31 * result + dateCreated.hashCode();
            result = 31 * result + lastUpdatedDate.hashCode();
            result = 31 * result + description.hashCode();
            return result;
        }
    }

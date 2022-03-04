package com.monster.address.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int GOVERNORATE_ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false)
    private Governorate governorate;

    private String town;

    private String city;

    private int code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGOVERNORATE_ID() {
        return GOVERNORATE_ID;
    }

    public void setGOVERNORATE_ID(int GOVERNORATE_ID) {
        this.GOVERNORATE_ID = GOVERNORATE_ID;
    }

    public Governorate getGovernorate() {
        return governorate;
    }

    public void setGovernorate(Governorate governorate) {
        this.governorate = governorate;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

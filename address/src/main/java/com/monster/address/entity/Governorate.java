package com.monster.address.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "governorate")
public class Governorate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int COUNTRY_ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false)
    private Country country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    public void setCOUNTRY_ID(int COUNTRY_ID) {
        this.COUNTRY_ID = COUNTRY_ID;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}

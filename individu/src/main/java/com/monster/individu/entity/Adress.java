package com.monster.individu.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "adress")
public class Adress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int TOWN_ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false)
    private Town town;

    private String ville;

    private String cite;

    private int code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTOWN_ID() {
        return TOWN_ID;
    }

    public void setTOWN_ID(int TOWN_ID) {
        this.TOWN_ID = TOWN_ID;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCite() {
        return cite;
    }

    public void setCite(String cite) {
        this.cite = cite;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

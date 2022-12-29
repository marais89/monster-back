package com.monster.individu.entity;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document(collection ="individu")
public class Individu implements Serializable {

    @Id
    private ObjectId id;

    private String nom;

    private String prenom;

    private String username;

    private int address_id;
    private String addressComplement;

    private String email;

    private String numeroTel;

    private LocalDate date_naissance;

    private LocalDate date_ceation;

    private int niveau;

    private String status;

    private Binary user_image;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public LocalDate getDate_ceation() {
        return date_ceation;
    }

    public void setDate_ceation(LocalDate date_ceation) {
        this.date_ceation = date_ceation;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String statut) {
        this.status = statut;
    }

    public Binary getUser_image() {
        return user_image;
    }

    public void setUser_image(Binary user_image) {
        this.user_image = user_image;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public void setAddressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
    }
}

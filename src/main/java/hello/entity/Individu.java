package hello.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "individu")
public class Individu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String nom;

    public String prenom;

    public String adresse;

    public String email;

    public String numeroTel;

    public LocalDate date_naissance;

    public LocalDate date_ceation;

    public int niveau;

    public String statut;

    public byte[] user_image;

}

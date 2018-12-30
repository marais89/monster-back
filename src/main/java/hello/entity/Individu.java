package hello.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "individu")
public class Individu {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String nom;

    private String prenom;

    private String adresse;

    private String email;

    private String numeroTel;

    private LocalDate date_naissance;

}

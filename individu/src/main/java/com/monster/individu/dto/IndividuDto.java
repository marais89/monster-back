package com.monster.individu.dto;

import com.monster.address.dto.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ApiModel
public class IndividuDto {

    private static int increment = 1;

    @ApiModelProperty(example = "0", name = "identifiant individu")
    public long id;

    @NotNull
    @ApiModelProperty(example = "toto", name = "nom individu")
    public String nom;

    @NotNull
    @ApiModelProperty(example = "titi", name = "prenom individu")
    public String prenom;

    @NotNull
    @ApiModelProperty(example = "tototi", name = "username/login de l'individu")
    public String username;

    @NotNull
    @ApiModelProperty(example = "tototi", name = "adresse details")
    public AddressDetailsDto addressDetails;

    @NotNull
    @ApiModelProperty(example = "tototi", name = "complément d'adresse")
    public String addressComplement;

    @NotNull
    @ApiModelProperty(example = "2011-07-14", name = "date de naissance de l'individu")
    public LocalDate date_naissance;

    @Email
    @ApiModelProperty(example = "toto@domaine.com", name = "adresse e-mail de l'individu")
    public String email;

    @NotNull
    @ApiModelProperty(example = "0623232323", name = "numero telephone de l'individu")
    public String numeroTel;

    @NotNull
    @ApiModelProperty(example = "2011-07-14", name = "date de création de l'individu")
    public LocalDate date_ceation;

    @NotNull
    @ApiModelProperty(example = "1", name = "niveau de l'individu")
    public int niveau;

    @NotNull
    @ApiModelProperty(example = "active", name = "statut de l'individu")
    public String status;

    @NotNull
    @ApiModelProperty(example = "$2y$zejkmjzenfiben456#", name = "mot de passe de l'individu  crypté")
    public String pass;

    @ApiModelProperty(example = "NULL", name = "image de l'individu")
    public byte[] user_image;

    @ApiModelProperty(example = "2011-12-18T13:17:19", name = "last connexion date time")
    public LocalDateTime lastConnexion;

}

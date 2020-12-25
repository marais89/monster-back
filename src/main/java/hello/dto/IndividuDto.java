package hello.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ApiModel
@NoArgsConstructor
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
    @ApiModelProperty(example = "5 rue de la liberte paris", name = "adresse individu")
    public String adresse;

    @NotNull
    @ApiModelProperty(example = "Tunis", name = "ville de l'individu")
    public String ville;

    @NotNull
    @ApiModelProperty(example = "7001", name = "code postale de l'individu")
    public int code_postale;

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
    public String statut;

    @NotNull
    @ApiModelProperty(example = "$2y$zejkmjzenfiben456#", name = "mot de passe de l'individu  crypté")
    public String pass;

    @ApiModelProperty(example = "NULL", name = "image de l'individu")
    public byte[] user_image;

}

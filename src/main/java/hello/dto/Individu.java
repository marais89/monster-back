package hello.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ApiModel
@AllArgsConstructor
@RequiredArgsConstructor
public class Individu {

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
    @ApiModelProperty(example = "2011-07-14", name = "date de naissance de l'individu")
    public LocalDate dateNaissance;

    @Email
    @ApiModelProperty(example = "toto@domaine.com", name = "adresse e-mail de l'individu")
    public String email;

    @NotNull
    @ApiModelProperty(example = "06 23 23 23 23", name = "numero telephone de l'individu")
    public String numTel;

}

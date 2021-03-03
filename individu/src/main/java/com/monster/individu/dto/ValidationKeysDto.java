package com.monster.individu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel
@NoArgsConstructor
public class ValidationKeysDto {

    public int id;

    @NotNull
    @ApiModelProperty(example = "toto@toto.com", name = "l'adresse email de l'utilisateur")
    public String username;

    @NotNull
    @ApiModelProperty(example = "MONSTER%_31012021_18370188_12345678", name = "la cle de validation de l'utilisateur")
    public String secret;

    @NotNull
    @ApiModelProperty(example = "31/01/2021 08:10", name = "date et heure de creation de la clef de validation ")
    public LocalDateTime creation_date;

    @NotNull
    @ApiModelProperty(example = "true", name = "boolean: si la clef de validation a été utilisé ou pas ")
    public boolean used;
}

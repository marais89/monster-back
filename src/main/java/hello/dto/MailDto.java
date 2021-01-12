package hello.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class MailDto {

    @NotNull
    @ApiModelProperty(example = "test@test.com", name = "adresse e-mail du destinataire")
    public String to;

    @NotNull
    @ApiModelProperty(example = "welcome", name = "Objet de l'E-mail")
    public String object;
}
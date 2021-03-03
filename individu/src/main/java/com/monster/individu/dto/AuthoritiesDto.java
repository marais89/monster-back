package com.monster.individu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AuthoritiesDto {

    @ApiModelProperty(example = "0", name = "identifiant")
    public Long id;

    @ApiModelProperty(example = "test@test.com", name = "username de l'utilisateur")
    public String username;

    @ApiModelProperty(example = "USER", name = "role de l'utilisateur")
    public String authority;

}

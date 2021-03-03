package com.monster.individu.dto;

import io.swagger.annotations.ApiModel;

@ApiModel
public class UserDto {

    public String username;
    public String password;
    public boolean enabled;

    public UserDto() {
    }
}

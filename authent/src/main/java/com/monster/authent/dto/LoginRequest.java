package com.monster.authent.dto;

import com.monster.history.dto.RequestContext;

import javax.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull
    public String loginInfos;

    public RequestContext requestContext;
}

package com.monster.authent.dto;


import com.monster.history.dto.RequestContext;

public class UpdatePwdWithKeyDto {

    public String key;
    public String newPassword;
    public RequestContext requestContext;
}

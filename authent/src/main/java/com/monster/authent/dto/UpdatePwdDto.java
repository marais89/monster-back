package com.monster.authent.dto;

import com.monster.history.dto.RequestContext;

public class UpdatePwdDto {

    public String login;
    public String oldPwd;
    public String newPwd;
    public RequestContext requestContext;

}

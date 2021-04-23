package com.monster.authent.controller;

import com.monster.authent.dto.*;
import com.monster.authent.facade.AuthentFacade;
import com.monster.individu.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.GeneralSecurityException;

@Valid
@Api(tags = "Authentication")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthentController {


    private final AuthentFacade authentFacade;

    public AuthentController(AuthentFacade authentFacade) {
        this.authentFacade = authentFacade;
    }

    @PostMapping("/loggedUser")
    @ApiOperation(value = "retieve user by username")
    public LoginResponse findLoggedUser(@RequestBody LoginRequest infoLogin) throws GeneralSecurityException {
        return authentFacade.login(infoLogin);
    }

    @PostMapping("/updatePwd")
    @ApiOperation(value = "Update user password")
    public UserDto updatePwd(@RequestBody UpdatePwdDto updatePwdDto) {
        return authentFacade.updatePwd(updatePwdDto);
    }

    @PostMapping("/updatePwdWithKey")
    @ApiOperation(value = "Update user passwordin case forgotten pwd")
    public UpdatePwdWithKeyResponse updatePwdWithKey(@RequestBody UpdatePwdWithKeyDto updatePwdWithKey) {
        return authentFacade.updatePwdWithKey(updatePwdWithKey);
    }
}

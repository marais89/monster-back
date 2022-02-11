package com.monster.notification.controller;

import com.monster.notification.dto.MailDto;
import com.monster.notification.facade.MailFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Valid
@Api(tags = "e-mail manager")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MailController {


    @Autowired
    private MailFacade mailFacade;


    @RequestMapping(path = "/mail/send", method = RequestMethod.POST)
    @ApiOperation(value = "envoi un e-mail", authorizations = @Authorization("jwt"))
    public void sendMail(@RequestBody MailDto mailDto) throws MessagingException {
        mailFacade.sendMail(mailDto);
    }

}

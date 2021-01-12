package hello.controller;

import hello.dto.MailDto;
import hello.facade.MailFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Valid
@Api(tags = "e-mail manager")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MailController {


    @Autowired
    private MailFacade mailFacade;


    @RequestMapping(path = "/mail/welcome", method = RequestMethod.POST)
    @ApiOperation(value = "envoi un e-mail")
    public void sendMail(@RequestBody MailDto mailDto){
        mailFacade.sendMail(mailDto);
    }

}

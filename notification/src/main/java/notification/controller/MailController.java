package notification.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import notification.dto.MailDto;
import notification.facade.MailFacade;
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


    @RequestMapping(path = "/mail/send", method = RequestMethod.POST)
    @ApiOperation(value = "envoi un e-mail")
    public void sendMail(@RequestBody MailDto mailDto) {
        mailFacade.sendMail(mailDto);
    }

}

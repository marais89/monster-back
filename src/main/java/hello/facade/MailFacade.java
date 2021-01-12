package hello.facade;

import hello.dto.MailDto;
import hello.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailFacade {

    @Autowired
    private MailService mailService;

    public void sendMail(MailDto mailDto){
        mailService.sendMail(mailDto);
    }

}

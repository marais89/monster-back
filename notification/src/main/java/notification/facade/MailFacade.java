package notification.facade;


import notification.dto.MailDto;
import notification.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class MailFacade {

    @Autowired
    private MailService mailService;

    public void sendMail(MailDto mailDto) throws MessagingException {
        mailService.sendMail(mailDto);
    }

}

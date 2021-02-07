package notification.service;


import notification.dto.MailDto;

import javax.mail.MessagingException;

public interface MailService {

    public void sendMail(MailDto mailDto) throws MessagingException;

}

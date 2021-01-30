package notification.service;


import notification.dto.MailDto;

public interface MailService {

    public void sendMail(MailDto mailDto);

}

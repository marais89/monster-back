package com.monster.notification.service;


import com.monster.notification.dto.MailDto;

import javax.mail.MessagingException;

public interface MailService {

    void sendMail(MailDto mailDto) throws MessagingException;

}

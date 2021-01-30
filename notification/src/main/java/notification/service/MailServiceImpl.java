package notification.service;


import lombok.RequiredArgsConstructor;
import notification.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendMail(MailDto mailDto) {
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        //TODO set e-amail content according to type value: 'WELCOME, INFO, ...'
        message.setTo(mailDto.to);
        message.setSubject(mailDto.object);
        message.setText(mailDto.content);

        // Send Message!
        this.emailSender.send(message);

    }
}

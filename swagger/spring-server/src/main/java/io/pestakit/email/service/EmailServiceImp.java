package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


/**
 * @see EmailService
 * @author Tano Iannetta
 */
@Component
public class EmailServiceImp implements EmailService {

    @Autowired
    public JavaMailSender mailSender;

    @Override
    public void sendSimpleMessage(Email email) {

        SimpleMailMessage mail = new SimpleMailMessage();


        // set email
        mail.setFrom(email.getSender());
        mail.setTo(email.getRecipients().toArray(new String[email.getRecipients().size()]));
        mail.setCc(email.getCarbonCopy().toArray(new String[email.getCarbonCopy().size()]));
        mail.setBcc(email.getBlindCarbonCopy().toArray(new String[email.getBlindCarbonCopy().size()]));
        mail.setSubject(email.getSubject());
        mail.setText(email.getBody());

        mailSender.send(mail);
    }
}

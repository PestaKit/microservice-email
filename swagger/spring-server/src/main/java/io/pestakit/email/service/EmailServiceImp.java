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

        SimpleMailMessage message = new SimpleMailMessage();

        // set email
        message.setFrom(email.getSender());
        message.setTo(email.getRecipients().toArray(new String[email.getRecipients().size()]));
        message.setCc(email.getCarbonCopy().toArray(new String[email.getCarbonCopy().size()]));
        message.setBcc(email.getBlindCarbonCopy().toArray(new String[email.getBlindCarbonCopy().size()]));
        message.setSubject(email.getSubject());
        message.setText("");

        mailSender.send(message);
    }
}

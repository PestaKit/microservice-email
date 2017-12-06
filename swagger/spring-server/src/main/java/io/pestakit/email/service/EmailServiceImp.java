package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * @see EmailService
 * @author Tano Iannetta
 */
@Component
public class EmailServiceImp implements EmailService {

    @Autowired
    public JavaMailSender mailSender;

    @Override
    public void sendHtmlEmail(Email email) throws MessagingException {

        /*SimpleMailMessage mail = new SimpleMailMessage();


        // set email
        mail.setFrom(email.getSender());
        mail.setTo(email.getRecipients().toArray(new String[email.getRecipients().size()]));
        mail.setCc(email.getCarbonCopy().toArray(new String[email.getCarbonCopy().size()]));
        mail.setBcc(email.getBlindCarbonCopy().toArray(new String[email.getBlindCarbonCopy().size()]));
        mail.setSubject(email.getSubject());
        mail.setText(email.getBody());


        mailSender.send(mail);*/


        MimeMessage message = mailSender.createMimeMessage();


        MimeMessageHelper helper = new MimeMessageHelper(message, false,"utf-8");
        message.setContent(email.getBody(), "text/html");
        helper.setFrom(email.getSender());
        helper.setTo(email.getRecipients().toArray(new String[email.getRecipients().size()]));
        helper.setCc(email.getCarbonCopy().toArray(new String[email.getCarbonCopy().size()]));
        helper.setBcc(email.getBlindCarbonCopy().toArray(new String[email.getBlindCarbonCopy().size()]));
        helper.setSubject(email.getSubject());
        mailSender.send(message);


    }
}

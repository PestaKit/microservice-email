package io.pestakit.email.service;

import io.pestakit.email.entities.EmailEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 *  @see TemplateService
 *  @author Wojciech Myszkorowski and Tano Iannetta
 */
@Component
public class TemplateServiceImp implements TemplateService {


    @Override
    public SimpleMailMessage createTemplate(EmailEntity JsonRetrieved) {

        // email that will be send
        SimpleMailMessage message = new SimpleMailMessage();











        // set email
        message.setFrom(JsonRetrieved.getSender());
        message.setTo(JsonRetrieved.getRecipients()
                .toArray(new String[JsonRetrieved.getRecipients().size()]));
        message.setCc(JsonRetrieved.getCarbonCopy()
                .toArray(new String[JsonRetrieved.getCarbonCopy().size()]));
        message.setBcc(JsonRetrieved.getBlindCarbonCopy()
                .toArray(new String[JsonRetrieved.getBlindCarbonCopy().size()]));
        message.setSubject(JsonRetrieved.getSubject());







        return message;

    }
}

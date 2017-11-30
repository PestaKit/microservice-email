package io.pestakit.email.service;

import io.pestakit.email.entities.EmailEntity;
import org.springframework.mail.SimpleMailMessage;

public class TemplateServiceImp implements TemplateService {
    @Override
    public SimpleMailMessage createTemplate(EmailEntity JsonRetrieved) {
        /*
        email on lui passe un tableau de parametre => map

         */
        SimpleMailMessage message = new SimpleMailMessage();

        // set email
        message.setFrom(JsonRetrieved.getSender());
        message.setTo(JsonRetrieved.getRecipients().toArray(new String[JsonRetrieved.getRecipients().size()]));
        message.setCc(JsonRetrieved.getCarbonCopy().toArray(new String[JsonRetrieved.getCarbonCopy().size()]));
        message.setBcc(JsonRetrieved.getBlindCarbonCopy().toArray(new String[JsonRetrieved.getBlindCarbonCopy().size()]));
        message.setSubject(JsonRetrieved.getSubject());
//        for(int i = 0; i < JsonRetrieved.getParameters().size();i++) {
//            message.setText(JsonRetrieved.getParameters().get(i).getValue());;
//        }







        return message;

    }
}

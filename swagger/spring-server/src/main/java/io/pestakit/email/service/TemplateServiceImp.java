package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;
import org.springframework.mail.SimpleMailMessage;

import java.util.Map;

public class TemplateServiceImp implements TemplateService {
    @Override
    public SimpleMailMessage createTemplate(Email emailreciev) {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setFrom(emailreciev.getSender());
        email.setTo(emailreciev.getRecipients().toArray(new String[emailreciev.getRecipients().size()]));
        email.setCc(emailreciev.getCarbonCopy().toArray(new String[emailreciev.getCarbonCopy().size()]));
        email.setBcc(emailreciev.getBlindCarbonCopy().toArray(new String[emailreciev.getBlindCarbonCopy().size()]));
        email.setSubject(email.getSubject());



        return email;

    }
}

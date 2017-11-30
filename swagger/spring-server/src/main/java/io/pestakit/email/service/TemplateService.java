package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;
import io.pestakit.email.entities.EmailEntity;
import org.springframework.mail.SimpleMailMessage;

import java.util.Map;

/**
 * This interface give methods to prepare an email
 * @author Wojchiech Myszkorowski and Tano Iannetta
 */
public interface TemplateService {

    /**
     * Take a email entity and inject values in his template
     * @param email email sent by the POST method
     * @return email ready to be send
     */
    SimpleMailMessage createTemplate(EmailEntity email);
}

package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;
import io.pestakit.email.entities.EmailEntity;
import org.springframework.mail.SimpleMailMessage;

import java.util.Map;

/**
 *
 */
public interface TemplateService {
    /**
     *
     * @param email
     * @return
     */
    SimpleMailMessage createTemplate(EmailEntity email);
}

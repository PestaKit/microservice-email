package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;
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
    SimpleMailMessage createTemplate(Email email);
}

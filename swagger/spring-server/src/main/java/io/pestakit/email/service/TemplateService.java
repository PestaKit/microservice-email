package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;

import java.util.Map;

/**
 *
 */
public interface TemplateService {
    /**
     *
     * @param email
     * @param url
     * @param ableau
     * @return
     */
    Email createTemplate(Email email, String url, Map<String,String>ableau);
}

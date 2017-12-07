package io.pestakit.email.service;

import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @see TemplateService
 * @author Tano Iannetta
 */
@Component
public class TemplateServiceImp implements TemplateService {
    @Override
    public List<String> getKeys(String template) {
        return null;
    }
}

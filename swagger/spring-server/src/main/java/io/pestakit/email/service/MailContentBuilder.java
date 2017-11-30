package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;
import io.pestakit.email.api.model.Parameter;
import io.pestakit.email.entities.EmailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to fill emails template
 * @author Tano Iannetta and Wojciech Myszkorowski
 */
@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    /**
     * Inject values in the template
     * @param templateEngine todo
     */
    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * Inject values of the parameters in the fields
     * of the template
     * @param parameters to inject
     * @return body of the email
     */
    public String buildContent(List<Parameter> parameters, String templateName) {

        // build content
        Context context = new Context();

        for (Parameter p: parameters) {
            context.setVariable(p.getKey(), p.getValue());
        }

        return templateEngine.process(templateName, context);



    }
}

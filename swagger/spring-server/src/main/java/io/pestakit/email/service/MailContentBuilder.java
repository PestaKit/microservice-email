package io.pestakit.email.service;

import io.pestakit.email.api.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.List;


/**
 * This class is used to fill emails template
 * @author Tano Iannetta and Wojciech Myszkorowski
 */
@Service
public class MailContentBuilder {

    @Autowired
    protected StaticTemplateService staticTemplateService;

    @Autowired
    public MailContentBuilder() {}

    /**
     * Inject values of the parameters in the fields
     * of the template
     * @param parameters to inject
     * @return body of the email
     */
    public String buildContent(List<Parameter> parameters, String text) {

        // build content
        Context context = new Context();

        for (Parameter p: parameters) {
            context.setVariable(p.getKey(), p.getValue());
        }
        return staticTemplateService.processTemplateCode(text, context);



    }
}

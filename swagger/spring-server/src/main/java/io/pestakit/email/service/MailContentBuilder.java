package io.pestakit.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * This class is used to fill emails template
 * @author Tano Iannetta and Wojciech Myszkorowski
 */
@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(String name, String company) {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("company", company);
        return templateEngine.process("exampleTemplate.html", context);
    }
}

package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;
import io.pestakit.email.entities.EmailEntity;
import io.pestakit.email.entities.ParameterEntity;
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



    // todo change class
    public SimpleMailMessage createTemplate(EmailEntity email) {


        SimpleMailMessage emailToSend = new SimpleMailMessage();
        Map parameters = new HashMap();


        // set headers
        emailToSend.setFrom(email.getSender());
        emailToSend.setTo(email.getRecipients()
                .toArray(new String[email.getRecipients().size()]));
        emailToSend.setCc(email.getCarbonCopy()
                .toArray(new String[email.getCarbonCopy().size()]));
        emailToSend.setBcc(email.getBlindCarbonCopy()
                .toArray(new String[email.getBlindCarbonCopy().size()]));
        emailToSend.setSubject(email.getSubject());



        // build content
        //String content = build(parameters);



    return null;



    }
    /**
     * Inject values in template
     * @param body
     * @return
     */
   /* private String build(Map parameters) {

        Context context = new Context();

        for(int i = 0; i < parameters.size(); i++)
        {
            String key = list.get(i).getKey();
            String value = list.get(i).getValue();
            context.setVariable(, body);
        }


        return templateEngine.process("test.html", context);
    }
*/


    //todo method that parse email entity

}

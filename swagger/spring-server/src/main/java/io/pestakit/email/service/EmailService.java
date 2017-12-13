package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;
import io.pestakit.email.api.model.Parameter;
import org.springframework.web.servlet.tags.Param;

import javax.mail.MessagingException;
import java.util.List;

/**
 * This interface give methods to send emails
 * @author Tano Iannetta
 */
public interface EmailService {

    /**
     * Send a simple email
     * @param email email to send
     */
    void sendHtmlEmail(Email email) throws MessagingException;


    /**
     * Check if parameters of the email match the parameters expected from the template
     * @param emailParameters email's parameters
     * @param templateParameters parameters in the template
     * @return true if parameters are correct, flase otherwise
     */
    boolean checkParameters(List<Parameter> emailParameters, List<String> templateParameters);


}


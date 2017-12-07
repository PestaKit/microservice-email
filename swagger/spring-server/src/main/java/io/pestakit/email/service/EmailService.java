package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;

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


    //todo implement
    // InvalidFiledExeption ou qqch comme ca
    boolean checkParameters(List<String> emailParameters, List<String> templateParameters);

    //meme taille
    // iterer avec find
}


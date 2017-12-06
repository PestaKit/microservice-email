package io.pestakit.email.service;

import io.pestakit.email.api.model.Email;

import javax.mail.MessagingException;

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
}

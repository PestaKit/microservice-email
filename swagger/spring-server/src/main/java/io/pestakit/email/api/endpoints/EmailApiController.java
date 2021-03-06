package io.pestakit.email.api.endpoints;

import io.pestakit.email.api.EmailsApi;
import io.pestakit.email.api.model.Email;
import io.pestakit.email.api.model.EmailPrepared;
import io.pestakit.email.api.model.Parameter;
import io.pestakit.email.entities.EmailEntity;
import io.pestakit.email.entities.TemplateEntity;
import io.pestakit.email.repositories.EmailRepository;
import io.pestakit.email.repositories.TemplateRepository;
import io.pestakit.email.service.EmailService;
import io.pestakit.email.service.MailContentBuilder;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

/**
 * Used to respond to email api requests
 * This class is used to handle the /emails endpoint
 * It will get emails from the GET request and
 * Send email with the POST request
 *
 * @author Loan Lassalle, Wojciech Myszkorowski, Jérémie Zanone and Tano Iannetta
 */
@Controller
public class EmailApiController implements EmailsApi {

    /**
     * Used to CRUD actions on tag table
     */
    @Autowired
    private EmailRepository emailRepository;

    /**
     * Used to CRUD actions on template table
     */
    @Autowired
    private TemplateRepository templateRepository;

    /**
     * Used to CRUD actions on email table
     */
    @Autowired
    private EmailService emailService;

    /**
     * Used to instance email's body from template and parameters
     */
    @Autowired
    private MailContentBuilder mailContentBuilder;

    /**
     * Process POST /emails requestphh
     * Create an email with an email prepared
     *
     * @param emailPrepared email prepared
     * @return response entity
     */
    @Override
    public ResponseEntity<Object> createEmail(@ApiParam(value = "Create an email", required = true) @RequestBody EmailPrepared emailPrepared) {

        // Prepare an email with headers, template and parameters
        EmailEntity entity = entity = toEmailEntity(emailPrepared);
        URI location = null;

        // Send the email
        try {
            emailService.sendHtmlEmail(toEmail(entity));

            emailRepository.save(entity);  // Save email in database

            location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();

            // Update email's URL in database
            entity.setUrl(location.toString());
            emailRepository.save(entity);

        } catch (MessagingException e) {
            throw new MailParseException("Error mail", e);
        }

        return ResponseEntity.created(location).build();
    }

    /**
     * Process GET /emails request
     * Get all emails
     *
     * @return all emails
     */
    @Override
    public ResponseEntity<List<Email>> getEmails() {
        List<Email> emails = new ArrayList<>();

        // Get all emails in database
        for (EmailEntity entity : emailRepository.findAll()) {
            emails.add(toEmail(entity));
        }

        return ResponseEntity.ok(emails);
    }

    /**
     * Process GET /emails/{id}?id= request
     * Get a tag
     *
     * @param id email ID
     * @return an email
     */
    @Override
    public ResponseEntity<Email> getEmail(@ApiParam(value = "email ID", required = true) @PathVariable("id") Long id) {

        // Get email in database
        Email email = toEmail(emailRepository.findOne(id));

        if (email != null) {
            return ResponseEntity.ok(email);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Transform an email prepared to an email entity
     * It will as well set the body with the given template
     * and parameters
     *
     * @param emailPrepared email prepared
     * @return an email entity
     */
    private EmailEntity toEmailEntity(EmailPrepared emailPrepared) {
        Properties properties = new Properties();
        EmailEntity entity = new EmailEntity();
        String endpointRequest = new String();

        entity.setSender(emailPrepared.getSender());
        entity.setRecipients(emailPrepared.getRecipients());
        entity.setCarbonCopy(emailPrepared.getCarbonCopy());
        entity.setBlindCarbonCopy(emailPrepared.getBlindCarbonCopy());
        entity.setSubject(emailPrepared.getSubject());

        TemplateEntity templateEntity = null;
        String body = "";

        // Get template in database
        try (InputStream resourceStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("application.properties")) {

            properties.load(resourceStream);
            String template = emailPrepared.getTemplate();

            int firstIndex = template.indexOf('/', template.indexOf('/') + 2) + 1;
            int lastIndex = template.lastIndexOf('/') + 1;

            endpointRequest = template.substring(firstIndex, lastIndex);
            Long id = Long.valueOf(template.substring(lastIndex));

            templateEntity = templateRepository.findOne(id);
        } catch (IOException | NumberFormatException e) {
            throw new InvalidParameterException(e.toString());
        }

        // Insert values in place of parameters
        if (endpointRequest.equals(properties.getProperty("endpoints.template"))
                && templateEntity != null) {
            List<Parameter> parametersList = emailPrepared.getParameters();

            // Check if parameters are corrects
            if (!emailService.checkParameters(parametersList, templateEntity.getParameters())) {
                throw new InvalidParameterException("Parameters do not match");
            }

            // Build content
            Context context = new Context();
            body = mailContentBuilder.buildContent(parametersList, templateEntity.getBody());
        } else {
            throw new InvalidParameterException("Template does not exist");
        }

        // Set body's email to send
        entity.setBody(body);

        return entity;
    }

    /**
     * Transform an email to an email entity
     *
     * @param email email
     * @return an email entity
     */
    private EmailEntity toEmailEntity(Email email) {
        EmailEntity entity = new EmailEntity();

        entity.setUrl(email.getUrl());
        entity.setSender(email.getSender());
        entity.setRecipients(email.getRecipients());
        entity.setCarbonCopy(email.getCarbonCopy());
        entity.setBlindCarbonCopy(email.getBlindCarbonCopy());
        entity.setSubject(email.getSubject());
        entity.setBody(email.getBody());

        return entity;
    }

    /**
     * Transform an email entity to an email
     *
     * @param entity email entity
     * @return an email
     */
    private Email toEmail(EmailEntity entity) {
        Email email = new Email();

        email.setUrl(entity.getUrl());
        email.setSender(entity.getSender());
        email.setRecipients(entity.getRecipients());
        email.setCarbonCopy(entity.getCarbonCopy());
        email.setBlindCarbonCopy(entity.getBlindCarbonCopy());
        email.setSubject(entity.getSubject());
        email.setBody(entity.getBody());

        return email;
    }
}

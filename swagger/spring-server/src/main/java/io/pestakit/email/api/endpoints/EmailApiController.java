package io.pestakit.email.api.endpoints;

import io.pestakit.email.api.EmailsApi;
import io.pestakit.email.api.model.Email;
import io.pestakit.email.api.model.Parameter;
import io.pestakit.email.entities.EmailEntity;
import io.pestakit.email.entities.ParameterEntity;
import io.pestakit.email.repositories.EmailRepository;
import io.pestakit.email.service.EmailService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

/**
 * This class is used to handle the /emails endpoint
 * It will get emails from the GET request and
 * Send email with the POST request
 * @author Loan Lassalle, Wojciech Myszkorowski, Jérémie Zanone and Tano Iannetta
 */
@Controller
public class EmailApiController implements EmailsApi {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    public EmailService emailService;

    /**
     * Process POST request
     * Save email in DB and send it
     * @param email to save and to send
     * @return todo ??
     */
    public ResponseEntity<Object> createEmail(@ApiParam(value = "", required = true) @Valid @RequestBody Email email) {
        try {
            EmailEntity emailEntity = toEmailEntity(email);
            emailRepository.save(emailEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(emailEntity.getId()).toUri();

            // Send the email
            emailService.sendSimpleMessage(email);

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /emails
     * @return
     */
    public ResponseEntity<List<Email>> getEmails() {
        try {
            List<Email> emails = new ArrayList<>();
            for (EmailEntity emailEntity : emailRepository.findAll()) {
                emails.add(toEmail(emailEntity));
            }

            return ResponseEntity.ok(emails);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /emails/{id}?id=
     * @param id
     * @return
     */
    public ResponseEntity<Email> getEmail(Long id) {
        try {
            return ResponseEntity.ok(toEmail(emailRepository.findOne(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Transform an email to an email entity
     * @param email
     * @return
     */
    private EmailEntity toEmailEntity(Email email) {
        EmailEntity entity = new EmailEntity();
        entity.setSender(email.getSender());
        entity.setRecipients(email.getRecipients());
        entity.setCarbonCopy(email.getCarbonCopy());
        entity.setBlindCarbonCopy(email.getBlindCarbonCopy());
        entity.setSubject(email.getSubject());
        entity.setParameters(toParametersEntities(email.getParameters()));
        return entity;
    }

    /**
     * Transform an email entity to an email
     * @param email
     * @return
     */
    private Email toEmail(EmailEntity entity) {
        Email email = new Email();
        email.setSender(entity.getSender());
        email.setRecipients(entity.getRecipients());
        email.setCarbonCopy(entity.getCarbonCopy());
        email.setBlindCarbonCopy(entity.getBlindCarbonCopy());
        email.setSubject(entity.getSubject());
        email.setParameters(toParameters(entity.getParameters()));
        return email;
    }

    /**
     * Tranform a list of parameters to a list of parameters entities
     * @param parameterEntities
     * @return
     */
    private List<ParameterEntity> toParametersEntities(List<Parameter> parameters) {
        List<ParameterEntity> parameterEntities = new ArrayList<>();

        for (Parameter parameter : parameters) {
            parameterEntities.add(toParameterEntity(parameter));
        }

        return parameterEntities;
    }

    /**
     * Tranform a list of parameters entities to a list of parameters
     * @param parameterEntities
     * @return
     */
    private List<Parameter> toParameters(List<ParameterEntity> parameterEntities) {
        List<Parameter> parameters = new ArrayList<>();

        for (ParameterEntity parameterEntity : parameterEntities) {
            parameters.add(toParameter(parameterEntity));
        }

        return parameters;
    }

    /**
     * Tranform a parameter entity to a parameter
     * @param entity
     * @return
     */
    private ParameterEntity toParameterEntity(Parameter parameter) {
        ParameterEntity entity = new ParameterEntity();
        entity.setKey(parameter.getKey());
        entity.setValue(parameter.getValue());
        return entity;
    }

    /**
     * Tranform a parameter to a parameter entity
     * @param entity
     * @return
     */
    private Parameter toParameter(ParameterEntity entity) {
        Parameter parameter = new Parameter();
        parameter.setKey(entity.getKey());
        parameter.setValue(entity.getValue());
        return parameter;
    }
}

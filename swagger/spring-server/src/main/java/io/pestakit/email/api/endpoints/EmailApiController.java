package io.pestakit.email.api.endpoints;

import io.pestakit.email.api.EmailsApi;
import io.pestakit.email.api.model.Email;
import io.pestakit.email.entities.EmailEntity;
import io.pestakit.email.repositories.EmailRepository;
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

@Controller
public class EmailApiController implements EmailsApi {

    @Autowired
    EmailRepository emailRepository;

    public ResponseEntity<Object> createEmail(@ApiParam(value = "", required = true) @Valid @RequestBody Email email) {
        EmailEntity newEmailEntity = toEmailEntity(email);
        emailRepository.save(newEmailEntity);
        Long id = newEmailEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newEmailEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<List<Email>> getEmails() {
        List<Email> emails = new ArrayList<>();
        for (EmailEntity emailEntity : emailRepository.findAll()) {
            emails.add(toEmail(emailEntity));
        }

        return ResponseEntity.ok(emails);
    }

    public ResponseEntity<Email> getEmail(Long id) {
        return ResponseEntity.ok(toEmail(emailRepository.findOne(id)));
    }

    private EmailEntity toEmailEntity(Email email) {
        EmailEntity entity = new EmailEntity();
        entity.setSender(email.getSender());
        entity.setRecipients(email.getRecipients());
        entity.setCarbonCopy(email.getCarbonCopy());
        entity.setBlindCarbonCopy(email.getBlindCarbonCopy());
        entity.setSubject(email.getSubject());
        entity.setBody(email.getBody());
        return entity;
    }

    private Email toEmail(EmailEntity entity) {
        Email email = new Email();
        email.setSender(entity.getSender());
        email.setRecipients(entity.getRecipients());
        email.setCarbonCopy(entity.getCarbonCopy());
        email.setBlindCarbonCopy(entity.getBlindCarbonCopy());
        email.setSubject(entity.getSubject());
        email.setBody(entity.getBody());
        return email;
    }
}

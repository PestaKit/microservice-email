package io.pestakit.email.api.endpoints;

import io.pestakit.email.api.PersonsApi;
import io.pestakit.email.api.model.Person;
import io.pestakit.email.entities.PersonEntity;
import io.pestakit.email.repositories.PersonRepository;
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
public class PersonApiController implements PersonsApi {

    @Autowired
    PersonRepository personRepository;

    public ResponseEntity<Object> createPerson(@ApiParam(value = "", required = true) @Valid @RequestBody Person person) {
        PersonEntity newPersonEntity = toPersonEntity(person);
        personRepository.save(newPersonEntity);
        Long id = newPersonEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newPersonEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = new ArrayList<>();
        for (PersonEntity personEntity : personRepository.findAll()) {
            persons.add(toPerson(personEntity));
        }

        return ResponseEntity.ok(persons);
    }

    public ResponseEntity<Person> getPerson(Long id) {
        return ResponseEntity.ok(toPerson(personRepository.findOne(id)));
    }

    private PersonEntity toPersonEntity(Person person) {
        PersonEntity entity = new PersonEntity();
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setEmailAddresses(person.getEmailAddresses());
        return entity;
    }

    private Person toPerson(PersonEntity entity) {
        Person person = new Person();
        person.setFirstName(entity.getFirstName());
        person.setLastName(entity.getLastName());
        person.setEmailAddresses(entity.getEmailAddresses());
        return person;
    }
}

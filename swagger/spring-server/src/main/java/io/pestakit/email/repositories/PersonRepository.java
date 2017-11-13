package io.pestakit.email.repositories;

import io.pestakit.email.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

}

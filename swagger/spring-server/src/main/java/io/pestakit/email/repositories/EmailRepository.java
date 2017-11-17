package io.pestakit.email.repositories;

import io.pestakit.email.entities.EmailEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<EmailEntity, Long> {

}

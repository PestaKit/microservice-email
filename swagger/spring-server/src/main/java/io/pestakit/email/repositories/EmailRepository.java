package io.pestakit.email.repositories;

import io.pestakit.email.entities.EmailEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Used to communicate with email table in database
 */
public interface EmailRepository extends CrudRepository<EmailEntity, Long> {

}

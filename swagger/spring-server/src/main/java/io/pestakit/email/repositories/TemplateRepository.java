package io.pestakit.email.repositories;

import io.pestakit.email.entities.TemplateEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Used to communicate with template table in database
 */
public interface TemplateRepository extends CrudRepository<TemplateEntity, Long> {

}

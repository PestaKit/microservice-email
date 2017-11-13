package io.pestakit.email.repositories;

import io.pestakit.email.entities.TemplateEntity;
import org.springframework.data.repository.CrudRepository;

public interface TemplateRepository extends CrudRepository<TemplateEntity, Long> {

}

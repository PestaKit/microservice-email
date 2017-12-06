package io.pestakit.email.repositories;

import io.pestakit.email.entities.TagEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Used to communicate with tag table in database
 */
public interface TagRepository extends CrudRepository<TagEntity, Long> {

}

package io.pestakit.email.repositories;

import io.pestakit.email.entities.TagEntity;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<TagEntity, Long> {

}

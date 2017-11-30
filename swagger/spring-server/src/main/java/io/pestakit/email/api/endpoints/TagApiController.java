package io.pestakit.email.api.endpoints;

import io.pestakit.email.api.TagsApi;
import io.pestakit.email.api.model.Tag;
import io.pestakit.email.entities.TagEntity;
import io.pestakit.email.repositories.TagRepository;
import io.pestakit.email.repositories.TemplateRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

/**
 *
 *
 * author: Loan Lassalle
 */
@Controller
public class TagApiController implements TagsApi {

    //TODO: Gérer les exceptions

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TemplateRepository templateRepository;

    /**
     * Process POST /tags request
     * Create a tag
     *
     * @param tag to create
     * @return TODO
     */
    @Override
    public ResponseEntity<Object> createTag(@ApiParam(value = "Create a tag", required = true) @RequestBody Tag tag) {
        TagEntity entity = toTagEntity(tag);

        templatesExist(entity);
        tagRepository.save(entity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        // Update tag's URL
        entity.setUrl(location.toString());
        tagRepository.save(entity);

        return ResponseEntity.created(location).build();
    }

    /**
     * Process GET /tags request
     * Get all tags
     *
     * @return all tags
     */
    @Override
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> tags = new ArrayList<>();

        for (TagEntity entity : tagRepository.findAll()) {
            tags.add(toTag(entity));
        }

        return ResponseEntity.ok(tags);
    }

    /**
     * Process GET /tags/{id}?id= request
     * Get a tag
     *
     * @param id tag ID
     * @return a tag
     */
    @Override
    public ResponseEntity<Tag> getTag(@ApiParam(value = "tag ID", required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(toTag(tagRepository.findOne(id)));
    }

    /**
     * Process DELETE /tags/{id}?id= request
     * Delete a tag
     *
     * @param id tag ID
     * @return TODO
     */
//    @Override
//    public ResponseEntity<Void> deleteTag(Long id) {
//        TagEntity entity = tagRepository.findOne(id);
//
//        if (entity == null) {
//            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//        } else {
//            tagRepository.delete(entity.getId());
//            return new ResponseEntity<Void>(HttpStatus.OK);
//        }
//    }

    /**
     * Check if tag's templates exist
     *
     * @param entity tag entity
     */
    private void templatesExist(TagEntity entity) {
        for (Iterator<String> iterator = entity.getTemplates().iterator(); iterator.hasNext(); ) {
            if (templateRepository.findOne(entity.getId()) == null) {
                iterator.remove();
            }
        }
    }

    /**
     * Tranform a tag entity to a tag
     *
     * @param tag tag
     * @return a tag entity
     */
    private TagEntity toTagEntity(Tag tag) {
        TagEntity entity = new TagEntity();

        entity.setName(tag.getName());
        entity.setUrl(tag.getUrl());
        entity.setTemplates(tag.getTemplates());

        return entity;
    }

    /**
     * Tranform a tag to a tag entity
     *
     * @param entity tag entity
     * @return a tag
     */
    private Tag toTag(TagEntity entity) {
        Tag tag = new Tag();

        tag.setName(entity.getName());
        tag.setUrl(entity.getUrl());
        tag.setTemplates(entity.getTemplates());

        return tag;
    }
}

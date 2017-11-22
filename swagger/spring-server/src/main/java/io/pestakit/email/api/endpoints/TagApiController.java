package io.pestakit.email.api.endpoints;

import io.pestakit.email.api.TagsApi;
import io.pestakit.email.api.model.Tag;
import io.pestakit.email.entities.TagEntity;
import io.pestakit.email.repositories.TagRepository;
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
public class TagApiController implements TagsApi {

    @Autowired
    TagRepository tagRepository;

    /**
     * Process POST request
     * Save tag in DB
     * @param tag to save
     * @return todo ??
     */
    public ResponseEntity<Object> createTag(@ApiParam(value = "", required = true) @Valid @RequestBody Tag tag) {
        try {
            TagEntity newTagEntity = toTagEntity(tag);
            tagRepository.save(newTagEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newTagEntity.getId()).toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /tags
     * @return
     */
    public ResponseEntity<List<Tag>> getTags() {
        try {
            List<Tag> tags = new ArrayList<>();
            for (TagEntity tagEntity : tagRepository.findAll()) {
                tags.add(toTag(tagEntity));
            }

            return ResponseEntity.ok(tags);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /tags/{id}?id=
     * @param id
     * @return
     */
    public ResponseEntity<Tag> getTag(Long id) {
        try {
            return ResponseEntity.ok(toTag(tagRepository.findOne(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    public ResponseEntity<Void> deleteTag(Long id) {
//        TagEntity tagEntity = tagRepository.findOne(id);
//
//        if (tagEntity == null) {
//            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//        } else {
//            tagRepository.delete(tagEntity.getId());
//            return new ResponseEntity<Void>(HttpStatus.OK);
//        }
//    }

    /**
     * Tranform a tag entity to a tag
     * @param entity
     * @return
     */
    private TagEntity toTagEntity(Tag tag) {
        TagEntity entity = new TagEntity();
        entity.setName(tag.getName());
        entity.setUrl(tag.getUrl());
        return entity;
    }

    /**
     * Tranform a tag to a tag entity
     * @param entity
     * @return
     */
    private Tag toTag(TagEntity entity) {
        Tag tag = new Tag();
        tag.setName(entity.getName());
        tag.setUrl(entity.getUrl());
        return tag;
    }
}

package io.pestakit.email.api.endpoints;

import io.pestakit.email.api.TagsApi;
import io.pestakit.email.api.model.Tag;
import io.pestakit.email.entities.TagEntity;
import io.pestakit.email.entities.TemplateEntity;
import io.pestakit.email.repositories.TagRepository;
import io.pestakit.email.repositories.TemplateRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class TagApiController implements TagsApi {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TemplateRepository templateRepository;

    /**
     * Process POST request
     * Save tag in DB
     * @param tag to save
     * @return todo ??
     */
    public ResponseEntity<Object> createTag(@ApiParam(value = "", required = true) @Valid @RequestBody Tag tag) {
        try {
            TagEntity entity = toTagEntity(tag);
            checkTemplatesExist(entity);
            tagRepository.save(entity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(entity.getId()).toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            e.printStackTrace();
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
            for (TagEntity entity : tagRepository.findAll()) {
                tags.add(toTag(entity));
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
    public ResponseEntity<Tag> getTag(@ApiParam(value = "tag ID", required = true) @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(toTag(tagRepository.findOne(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /tags/{id}?id=
     *
     * @param id
     * @return
     */
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
    private void checkTemplatesExist(TagEntity entity) {
        for (Iterator<String> iterator = entity.getTemplatesUrl().iterator(); iterator.hasNext(); ) {
            String url = iterator.next();

            Long id = Long.valueOf(url.substring(url.lastIndexOf('/') + 1));
            TemplateEntity templateEntity = templateRepository.findOne(id);

            if (templateEntity == null) {
                iterator.remove();
            }
        }
    }

    /**
     * Tranform a tag entity to a tag
     * @param tag
     * @return
     */
    private TagEntity toTagEntity(Tag tag) {
        TagEntity entity = new TagEntity();
        entity.setName(tag.getName());
        entity.setUrl(tag.getUrl());
        entity.setTemplatesUrl(tag.getTemplatesUrl());
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
        tag.setTemplatesUrl(entity.getTemplatesUrl());
        return tag;
    }
}

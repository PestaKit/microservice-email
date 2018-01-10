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

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

/**
 * Used to respond to tag api requests
 *
 * @author Loan Lassalle
 */
@Controller
public class TagApiController implements TagsApi {

//     TODO: Exceptions des actions CRUD
//     TODO: Retour des fonctions

    /**
     * Used to CRUD actions on tag table
     */
    @Autowired
    private TagRepository tagRepository;

    /**
     * Used to CRUD actions on template table
     */
    @Autowired
    private TemplateRepository templateRepository;

    /**
     * Process POST /tags request
     * Create a tag
     *
     * @param tag tag to create
     * @return TODO
     */
    @Override
    public ResponseEntity<Object> createTag(@ApiParam(value = "Create a tag", required = true) @RequestBody Tag tag) {

        // Create tag in database
        TagEntity entity = toTagEntity(tag);
        tagRepository.save(entity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        // Update tag's URL in database
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

        // Get all tags in database
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

        // Get tag in database
        Tag tag = toTag(tagRepository.findOne(id));

        if (tag != null) {
            return ResponseEntity.ok(tag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Process PUT /tags/{id}?id= request
     * Update a tag
     *
     * @param id tag ID
     * @return TODO
     */
    @Override
    public ResponseEntity<Void> updateTag(@ApiParam(value = "tag ID", required = true) @PathVariable("id") String id,
                                          @ApiParam(value = "Tag", required = true) @RequestBody Tag tag) {
        // Get tag in database
        TagEntity entity = tagRepository.findOne(Long.valueOf(id));

        // Update tag in database
        if (entity != null) {
            entity.setName(tag.getName());
            entity.setTemplates(tag.getTemplates());
            tagRepository.save(entity);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Process DELETE /tags/{id}?id= request
     * Delete a tag
     *
     * @param id tag ID
     * @return TODO
     */
    @Override
    public ResponseEntity<Void> deleteTag(@ApiParam(value = "tag ID", required = true) @PathVariable("id") Long id) {

        // Get tag in database
        TagEntity entity = tagRepository.findOne(id);

        // Delete tag in database
        if (entity != null) {
            tagRepository.delete(entity.getId());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Check if tag's templates exist
     *
     * @param templates tag's templates
     */
    private List<String> templatesIfExist(List<String> templates) {
        Properties properties = new Properties();

        for (Iterator<String> iterator = templates.iterator(); iterator.hasNext(); ) {
            String template = iterator.next();

            try (InputStream resourceStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties")) {

                properties.load(resourceStream);

                int firstIndex = template.indexOf('/', template.indexOf('/') + 2) + 1;
                int lastIndex = template.lastIndexOf('/') + 1;

                String endpointRequest = template.substring(firstIndex, lastIndex);
                Long id = Long.valueOf(template.substring(lastIndex));

                // Get tag in database
                if (!endpointRequest.equals(properties.getProperty("endpoints.template"))
                        || templateRepository.findOne(id) == null) {
                    iterator.remove();
                }
            } catch (IOException | NumberFormatException e) {
                iterator.remove();
            }
        }

        return templates;
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
        entity.setTemplates(templatesIfExist(tag.getTemplates()));

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
        tag.setTemplates(templatesIfExist(entity.getTemplates()));

        return tag;
    }
}

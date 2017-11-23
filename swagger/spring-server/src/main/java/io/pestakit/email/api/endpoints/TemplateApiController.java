package io.pestakit.email.api.endpoints;

import io.pestakit.email.api.TemplatesApi;
import io.pestakit.email.api.model.Tag;
import io.pestakit.email.api.model.Template;
import io.pestakit.email.entities.TagEntity;
import io.pestakit.email.entities.TemplateEntity;
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
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class TemplateApiController implements TemplatesApi {

    @Autowired
    TemplateRepository templateRepository;

    /**
     * Process POST request
     * Save template in DB
     * @param template to save
     * @return todo ??
     */
    public ResponseEntity<Object> createTemplate(@ApiParam(value = "Create a template", required = true) @RequestBody Template template) {
        try {
            TemplateEntity newTemplateEntity = toTemplateEntity(template);
            templateRepository.save(newTemplateEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newTemplateEntity.getId()).toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /templates
     * @return
     */
    public ResponseEntity<List<Template>> getTemplates() {
        try {
            List<Template> templates = new ArrayList<>();
            for (TemplateEntity entity : templateRepository.findAll()) {
                templates.add(toTemplate(entity));
            }

            return ResponseEntity.ok(templates);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /templates/{id}?id=
     * @param id
     * @return
     */
    public ResponseEntity<Template> getTemplate(@ApiParam(value = "template ID", required = true) @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(toTemplate(templateRepository.findOne(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Transform a template to template entity
     * @param template
     * @return
     */
    private TemplateEntity toTemplateEntity(Template template) {
        TemplateEntity entity = new TemplateEntity();
        entity.setUrl(template.getUrl());
        entity.setName(template.getName());
        entity.setTags(toTagsEntities(template.getTags()));
        entity.setParameters(template.getParameters());
        entity.setBody(template.getBody());
        return entity;
    }

    /**
     * Transform a template entity to template
     * @param entity
     * @return
     */
    private Template toTemplate(TemplateEntity entity) {
        Template template = new Template();
        template.setUrl(entity.getUrl());
        template.setName(entity.getName());
        template.setTags(toTags(entity.getTags()));
        template.setParameters(entity.getParameters());
        template.setBody(entity.getBody());
        return template;
    }

    /**
     * Transform a tags to tags entities
     * @param tags
     * @return
     */
    private List<TagEntity> toTagsEntities(List<Tag> tags) {
        List<TagEntity> entities = new ArrayList<>();

        for (Tag tag : tags) {
            entities.add(toTagEntity(tag));
        }

        return entities;
    }

    /**
     * Transform a tags entities to tags
     * @param entities
     * @return
     */
    private List<Tag> toTags(List<TagEntity> entities) {
        List<Tag> tags = new ArrayList<>();

        for (TagEntity entity : entities) {
            tags.add(toTag(entity));
        }

        return tags;
    }

    /**
     * Tranform a tag to tag entity
     * @param tag
     * @return
     */
    private TagEntity toTagEntity(Tag tag) {
        TagEntity entity = new TagEntity();
        entity.setName(tag.getName());
        entity.setUrl(tag.getUrl());
        return entity;
    }

    /**
     * Tranform a tag entity to tag
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

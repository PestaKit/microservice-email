package io.pestakit.email.api.endpoints;

import io.pestakit.email.api.TemplatesApi;
import io.pestakit.email.api.model.Template;
import io.pestakit.email.api.model.Tag;
import io.pestakit.email.entities.TagEntity;
import io.pestakit.email.entities.TemplateEntity;
import io.pestakit.email.repositories.TemplateRepository;
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
public class TemplateApiController implements TemplatesApi {

    @Autowired
    TemplateRepository templateRepository;

    public ResponseEntity<Object> createTemplate(@ApiParam(value = "", required = true) @Valid @RequestBody Template template) {
        TemplateEntity newTemplateEntity = toTemplateEntity(template);
        templateRepository.save(newTemplateEntity);
        Long id = newTemplateEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newTemplateEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<List<Template>> getTemplates() {
        List<Template> templates = new ArrayList<>();
        for (TemplateEntity templateEntity : templateRepository.findAll()) {
            templates.add(toTemplate(templateEntity));
        }

        return ResponseEntity.ok(templates);
    }

    public ResponseEntity<Template> getTemplate(Long id) {
        return ResponseEntity.ok(toTemplate(templateRepository.findOne(id)));
    }

    private TemplateEntity toTemplateEntity(Template template) {
        TemplateEntity entity = new TemplateEntity();
        entity.setUrl(template.getUrl());
        entity.setName(template.getName());
        entity.setTags(toTagsEntity(template.getTags()));
        entity.setParameters(template.getParameters());
        entity.setBody(template.getBody());
        return entity;
    }

    private Template toTemplate(TemplateEntity entity) {
        Template template = new Template();
        template.setUrl(entity.getUrl());
        template.setName(entity.getName());
        template.setTags(toTags(entity.getTags()));
        template.setParameters(entity.getParameters());
        template.setBody(entity.getBody());
        return template;
    }

    private List<TagEntity> toTagsEntity(List<Tag> tags) {
        List<TagEntity> tagEntities = new ArrayList<>();

        for (Tag tag : tags) {
            tagEntities.add(toTagEntity(tag));
        }

        return tagEntities;
    }

    private List<Tag> toTags(List<TagEntity> tagEntities) {
        List<Tag> tags = new ArrayList<>();

        for (TagEntity tagEntity : tagEntities) {
            tags.add(toTag(tagEntity));
        }

        return tags;
    }

    private TagEntity toTagEntity(Tag tag) {
        TagEntity entity = new TagEntity();
        entity.setName(tag.getName());
        entity.setUrl(tag.getUrl());
        return entity;
    }

    private Tag toTag(TagEntity entity) {
        Tag tag = new Tag();
        tag.setName(entity.getName());
        tag.setUrl(entity.getUrl());
        return tag;
    }
}

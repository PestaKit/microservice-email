package io.pestakit.email.api.endpoints;

import io.pestakit.email.api.TemplatesApi;
import io.pestakit.email.api.model.Template;
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
public class TemplateApiController implements TemplatesApi {

    //TODO: Gérer les exceptions

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TemplateRepository templateRepository;

    /**
     * Process POST /templates request
     * Create a Template
     *
     * @param template template to create
     * @return TODO
     */
    @Override
    public ResponseEntity<Object> createTemplate(@ApiParam(value = "Create a template", required = true) @RequestBody Template template) {
        TemplateEntity entity = toTemplateEntity(template);

        tagsExist(entity);
        templateRepository.save(entity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        // Update template's URL
        entity.setUrl(location.toString());
        templateRepository.save(entity);

        return ResponseEntity.created(location).build();
    }

    /**
     * Process GET /templates request
     * Get all templates
     *
     * @return all templates
     */
    @Override
    public ResponseEntity<List<Template>> getTemplates() {
        List<Template> templates = new ArrayList<>();

        for (TemplateEntity entity : templateRepository.findAll()) {
            templates.add(toTemplate(entity));
        }

        return ResponseEntity.ok(templates);
    }

    /**
     * Process GET /templates/{id}?id= request
     * Get a template
     *
     * @param id template ID
     * @return a template
     */
    @Override
    public ResponseEntity<Template> getTemplate(@ApiParam(value = "template ID", required = true) @PathVariable("id") Long id) {
        return ResponseEntity.ok(toTemplate(templateRepository.findOne(id)));
    }

    /**
     * Check if template's tags exist
     *
     * @param entity template entity
     */
    private void tagsExist(TemplateEntity entity) {
        for (Iterator<String> iterator = entity.getTags().iterator(); iterator.hasNext(); ) {
            if (tagRepository.findOne(entity.getId()) == null) {
                iterator.remove();
            }
        }
    }

    /**
     * Transform a template to template entity
     * @param template template
     * @return a template entity
     */
    private TemplateEntity toTemplateEntity(Template template) {
        TemplateEntity entity = new TemplateEntity();

        entity.setUrl(template.getUrl());
        entity.setName(template.getName());
        entity.setTags(template.getTags());
        entity.setParameters(template.getParameters());
        entity.setBody(template.getBody());

        return entity;
    }

    /**
     * Transform a template entity to template
     * @param entity template entity
     * @return a template
     */
    private Template toTemplate(TemplateEntity entity) {
        Template template = new Template();

        template.setUrl(entity.getUrl());
        template.setName(entity.getName());
        template.setTags(entity.getTags());
        template.setParameters(entity.getParameters());
        template.setBody(entity.getBody());

        return template;
    }
}

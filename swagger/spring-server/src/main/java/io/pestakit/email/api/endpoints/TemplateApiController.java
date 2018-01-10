package io.pestakit.email.api.endpoints;

import io.pestakit.email.api.TemplatesApi;
import io.pestakit.email.api.model.Template;
import io.pestakit.email.api.model.TemplateBody;
import io.pestakit.email.entities.TemplateEntity;
import io.pestakit.email.repositories.TagRepository;
import io.pestakit.email.repositories.TemplateRepository;
import io.pestakit.email.service.TemplateService;
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
 * Used to respond to template api requests
 *
 * @author Loan Lassalle
 */
@Controller
public class TemplateApiController implements TemplatesApi {

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
     * Used to process template
     */
    @Autowired
    private TemplateService templateService;

    /**
     * Process POST /templates request
     * Create a template
     *
     * @param template template to create
     * @return TODO
     */
    @Override
    public ResponseEntity<Object> createTemplate(@ApiParam(value = "Create a template", required = true) @RequestBody TemplateBody template) {

        // Create template in database
        TemplateEntity entity = toTemplateEntity(template);

        templateRepository.save(entity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        // Update template's URL in database
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

        // Get all template in database
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

        // Get template in database
        Template template = toTemplate(templateRepository.findOne(id));

        if (template != null) {
            return ResponseEntity.ok(template);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Process PUT /templates/{id}?id= request
     * Update a template
     *
     * @param id template ID
     * @return TODO
     */
    @Override
    public ResponseEntity<Void> updateTemplate(@ApiParam(value = "template ID", required = true) @PathVariable("id") String id,
                                               @ApiParam(value = "Template", required = true) @RequestBody TemplateBody template) {
        // Get template in database
        TemplateEntity entity = templateRepository.findOne(Long.valueOf(id));

        // Update template in database
        if (entity != null) {
            entity.setName(template.getName());
            entity.setTags(template.getTags());

            // Extract parameters form the template
            String body = template.getBody();
            entity.setParameters(templateService.getParameters(body));
            entity.setBody(body);

            templateRepository.save(entity);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Process DELETE /templates/{id}?id= request
     * Delete a template
     *
     * @param id template ID
     * @return TODO
     */
    @Override
    public ResponseEntity<Void> deleteTemplate(@ApiParam(value = "template ID", required = true) @PathVariable("id") Long id) {

        // Get template in database
        TemplateEntity entity = templateRepository.findOne(id);

        // Delete template in database
        if (entity != null) {
            templateRepository.delete(entity.getId());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Check if template's tags exist
     *
     * @param tags template's tags
     */
    private List<String> tagsIfExist(List<String> tags) {
        Properties properties = new Properties();

        for (Iterator<String> iterator = tags.iterator(); iterator.hasNext(); ) {
            String tag = iterator.next();

            try (InputStream resourceStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties")) {

                properties.load(resourceStream);

                int firstIndex = tag.indexOf('/', tag.indexOf('/') + 2) + 1;
                int lastIndex = tag.lastIndexOf('/') + 1;

                String endpointRequest = tag.substring(firstIndex, lastIndex);
                Long id = Long.valueOf(tag.substring(lastIndex));

                // Get template in database
                if (!endpointRequest.equals(properties.getProperty("endpoints.tag"))
                        || tagRepository.findOne(id) == null) {
                    iterator.remove();
                }
            } catch (IOException | NumberFormatException e) {
                iterator.remove();
            }
        }

        return tags;
    }

    /**
     * Transform a template to template entity
     *
     * @param template template
     * @return a template entity
     */
    private TemplateEntity toTemplateEntity(TemplateBody template) {
        TemplateEntity entity = new TemplateEntity();

        entity.setUrl(template.getUrl());
        entity.setName(template.getName());
        entity.setTags(tagsIfExist(template.getTags()));

        // Extract parameters form the template
        String body = template.getBody();
        entity.setParameters(templateService.getParameters(body));
        entity.setBody(body);

        return entity;
    }

    /**
     * Transform a template entity to template
     *
     * @param entity template entity
     * @return a template
     */
    private Template toTemplate(TemplateEntity entity) {
        Template template = new Template();

        template.setUrl(entity.getUrl());
        template.setName(entity.getName());
        template.setTags(tagsIfExist(entity.getTags()));
        template.setParameters(entity.getParameters());
        template.setBody(entity.getBody());

        return template;
    }
}

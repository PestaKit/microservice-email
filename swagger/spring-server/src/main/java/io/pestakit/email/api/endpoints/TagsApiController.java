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
public class TagsApiController implements TagsApi {

    @Autowired
    TagRepository tagRepository;

    public ResponseEntity<Object> createTag(@ApiParam(value = "", required = true) @Valid @RequestBody Tag tag) {
        TagEntity newTagEntity = toTagEntity(tag);
        tagRepository.save(newTagEntity);
        Long id = newTagEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newTagEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> tags = new ArrayList<>();
        for (TagEntity tagEntity : tagRepository.findAll()) {
            tags.add(toTag(tagEntity));
        }

        return ResponseEntity.ok(tags);
    }

    private TagEntity toTagEntity(Tag tag) {
        TagEntity entity = new TagEntity();
        entity.setName(tag.getName());
        return entity;
    }

    private Tag toTag(TagEntity entity) {
        Tag tag = new Tag();
        tag.setName(entity.getName());
        return tag;
    }
}

package io.pestakit.email.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class TemplateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;
    private String name;

//    @ManyToMany(mappedBy = "tags")
//    private List<TagEntity> tags;

    //    private List<String> parameters;
    private String body;

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<TagEntity> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<TagEntity> tags) {
//        this.tags = tags;
//    }
//
//    public void addTag(TagEntity tag) {
//        tags.add(tag);
//    }
//
//    public void removeTag(TagEntity tag) {
//        tags.remove(tag);
//    }
//
//    public List<String> getParameters() {
//        return parameters;
//    }
//
//    public void setParameters(List<String> parameters) {
//        this.parameters = parameters;
//    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

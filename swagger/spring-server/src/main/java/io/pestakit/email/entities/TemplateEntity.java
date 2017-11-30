package io.pestakit.email.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * author: Loan Lassalle
 */
@Entity
public class TemplateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;
    private String name;

    @ElementCollection
    private List<String> tags;

    @ElementCollection
    private List<String> parameters;

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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }

        tags.add(tag);
    }

    public void removeTag(String tag) {
        if (tags != null) {
            tags.remove(tag);
        }
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String parameter) {
        if (parameters == null) {
            parameters = new ArrayList<>();
        }

        parameters.add(parameter);
    }

    public void removeParameter(String parameter) {
        if (parameters != null) {
            parameters.remove(parameter);
        }
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

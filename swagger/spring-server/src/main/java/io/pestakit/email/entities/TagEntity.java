package io.pestakit.email.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;
    private String name;

    @ElementCollection
    private List<String> templatesUrl;

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

    public List<String> getTemplatesUrl() {
        return templatesUrl;
    }

    public void setTemplatesUrl(List<String> templatesUrl) {
        this.templatesUrl = templatesUrl;
    }

    public void addTemplateUrl(String templateUrl) {
        if (templatesUrl == null) {
            templatesUrl = new ArrayList<>();
        }

        templatesUrl.add(templateUrl);
    }

    public void removeTemplateUrl(String templateUrl) {
        if (templatesUrl != null) {
            templatesUrl.remove(templateUrl);
        }
    }
}

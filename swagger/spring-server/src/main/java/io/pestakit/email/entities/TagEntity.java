package io.pestakit.email.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Description
 * author: Loan Lassalle
 */
@Entity
public class TagEntity implements Serializable {

    // TODO: JavaDoc et commentaires

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;
    private String name;

    @ElementCollection
    private List<String> templates;

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

    public List<String> getTemplates() {
        return templates;
    }

    public void setTemplates(List<String> templates) {
        this.templates = templates;
    }

    public void addTemplate(String template) {
        if (templates == null) {
            templates = new ArrayList<>();
        }

        templates.add(template);
    }

    public void removeTemplate(String template) {
        if (templates != null) {
            templates.remove(template);
        }
    }
}

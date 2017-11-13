package io.pestakit.email.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class TagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;
    private String name;

//    @ManyToMany
//    private List<TemplateEntity> templates;

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

//    public List<TemplateEntity> getTemplates() {
//        return templates;
//    }
//
//    public void setTemplates(List<TemplateEntity> templates) {
//        this.templates = templates;
//    }
//
//    public void addTemplate(TemplateEntity template) {
//        templates.add(template);
//    }
//
//    public void removeTemplate(TemplateEntity template) {
//        templates.remove(template);
//    }
}

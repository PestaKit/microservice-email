package io.pestakit.email.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to organize templates by tags or categories
 *
 * author: Loan Lassalle
 */
@Entity
public class TagEntity implements Serializable {

    // TODO: JavaDoc et commentaires

    /**
     * Tag's ID
     * Used to identify tag by database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Tag's url
     * Used to get the tag
     */
    private String url;

    /**
     * Tag's name
     * Used to identify tag by user
     */
    private String name;

    /**
     * List of template's url
     * Used to associate templates to tag
     */
    @ElementCollection
    private List<String> templates;

    /**
     * Get tag's ID
     *
     * @return tag's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Get tag's url
     *
     * @return tag's url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set tag's url
     *
     * @param url tag's url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get tag's name
     *
     * @return tag's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set tag's name
     *
     * @param name tag's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get tag's template list
     *
     * @return tag's template list
     */
    public List<String> getTemplates() {
        return templates;
    }

    /**
     * Set tag's template list
     *
     * @param templates tag's template list to set
     */
    public void setTemplates(List<String> templates) {
        this.templates = templates;
    }

    /**
     * Add a template's url in the template list
     *
     * @param template template's url to add
     */
    public void addTemplate(String template) {
        if (templates == null) {
            templates = new ArrayList<>();
        }

        templates.add(template);
    }

    /**
     * Remove a template's url in the template list
     *
     * @param template template's url to remove
     */
    public void removeTemplate(String template) {
        if (templates != null) {
            templates.remove(template);
        }
    }
}

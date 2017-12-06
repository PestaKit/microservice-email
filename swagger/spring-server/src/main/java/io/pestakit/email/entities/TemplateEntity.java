package io.pestakit.email.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to create email's body with
 * replace specific values (parameters) by
 * values provided by user
 *
 * author: Loan Lassalle
 */
@Entity
public class TemplateEntity implements Serializable {

    /**
     * Template's ID
     * Used to identify template by database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Template's url
     * Used to get the template
     */
    private String url;

    /**
     * Template's name
     * Used to identify template by user
     */
    private String name;

    /**
     * List of tag's url
     * Used to associate tags to tag
     */
    // TODO: Voir comment utiliser une class ShortTag
    @ElementCollection
    private List<String> tags;

    /**
     * List of template's url
     * Used to associate templates to tag
     */
    @ElementCollection
    private List<String> parameters;

    private String body;

    /**
     * Get template's ID
     *
     * @return template's ID
     */
    public long getId() {
        return id;
    }

    /**
     * Get template's url
     *
     * @return template's url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set template's url
     *
     * @param url template's url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get template's name
     *
     * @return name template's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set template's name
     *
     * @param name template's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get template's tag list
     *
     * @return tags template's tag list
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Set template's tag list
     *
     * @param tags template's tag list to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Add a tag's url in the tag list
     *
     * @param tag tag's url to add
     */
    public void addTag(String tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }

        tags.add(tag);
    }

    /**
     * Remove a tag's url in the tag list
     *
     * @param tag tag's url to remove
     */
    public void removeTag(String tag) {
        if (tags != null) {
            tags.remove(tag);
        }
    }

    /**
     * Get template's parameter list
     *
     * @return template's parameter list
     */
    public List<String> getParameters() {
        return parameters;
    }

    /**
     * Set template's parameter list
     *
     * @param parameters template's parameter list
     */
    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    /**
     * Add a parameter in the parameter list
     *
     * @param parameter parameter to add
     */
    public void addParameter(String parameter) {
        if (parameters == null) {
            parameters = new ArrayList<>();
        }

        parameters.add(parameter);
    }

    /**
     * Remove a parameter in the parameter list
     *
     * @param parameter parameter to remove
     */
    public void removeParameter(String parameter) {
        if (parameters != null) {
            parameters.remove(parameter);
        }
    }

    /**
     * Get template's body
     *
     * @return body template's body
     */
    public String getBody() {
        return body;
    }

    /**
     * Set template's body
     *
     * @param body template's body to set
     */
    public void setBody(String body) {
        this.body = body;
    }
}

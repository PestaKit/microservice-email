package io.pestakit.email.service;


import java.util.List;

/**
 * This class is used to process templates.
 * It allow to extract the keys in a template
 * @author Tano Iannetta
 */
public interface TemplateService {

    /**
     * Extract keys parameters from a body of a template
     * @param template from which extract keys
     * @return list of all keys contained in the template
     */
    List<String> getKeys(String template);
}

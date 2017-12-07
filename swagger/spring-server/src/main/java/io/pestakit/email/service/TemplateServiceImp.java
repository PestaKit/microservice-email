package io.pestakit.email.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * @see TemplateService
 * @author Tano Iannetta
 */
@Component
public class TemplateServiceImp implements TemplateService {

    private static final String REGEX = "\\$\\{(.*?)}";

    @Override
    public List<String> getParameters(String template) {

        List<String> parameters = new ArrayList<>();
        try {
            Pattern regex = Pattern.compile(REGEX);
            Matcher matcher = regex.matcher(template);
            while (matcher.find())
            {
                parameters.add(matcher.group(1));
            }
        }catch (PatternSyntaxException e)
        {
            e.printStackTrace();
        }
        return parameters;
    }
}

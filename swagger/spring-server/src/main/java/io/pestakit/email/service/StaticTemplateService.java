package io.pestakit.email.service;


import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.context.IContext;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;
import org.thymeleaf.templateresolver.ITemplateResolutionValidity;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.NonCacheableTemplateResolutionValidity;
import org.thymeleaf.templateresolver.TemplateResolution;
import org.thymeleaf.util.Validate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Ref: https://github.com/thymeleaf/thymeleaf-itutorial/blob/2.1-master/src/test/java/org/thymeleaf/tools/memoryexecutor/StaticTemplateExecutorTest.java
 * @author anandchakru
 *
 */
@Service
public class StaticTemplateService {

    public String processTemplateCode(final String code, final IContext context) {

        Validate.notNull(code, "Code must be non-null");
        Validate.notNull(context, "Context must be non-null");
        String templateMode = StandardTemplateModeHandlers.HTML5.getTemplateModeName();
        IMessageResolver messageResolver = new StandardMessageResolver();
        ITemplateResolver templateResolver = new MemoryTemplateResolver(code, templateMode);
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setMessageResolver(messageResolver);
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.initialize();
        System.out.println(code);
        System.out.println(context.getVariables());
        return templateEngine.process("dummy", context);
    }
}

class FixedMemoryResourceResolver implements IResourceResolver {
    private static final String NAME = "FixedMemoryResourceResolver";
    private final String templateContent;

    public FixedMemoryResourceResolver(final String templateContent) {
        Validate.notNull(templateContent, "Template content must be non-null");
        this.templateContent = templateContent;
    }
    @Override
    public String getName() {
        return NAME;
    }
    @Override
    public InputStream getResourceAsStream(final TemplateProcessingParameters tpp, final String templateName) {
        return new ByteArrayInputStream(templateContent.getBytes());
    }
}

class MemoryTemplateResolver implements ITemplateResolver {
    private static final String NAME = "MemoryTemplateResolver";
    private static final Integer ORDER = 1;
    private final String templateContent;
    private final String templateMode;

    public MemoryTemplateResolver(final String templateContent, final String templateMode) {
        Validate.notNull(templateContent, "Template content must be non-null");
        Validate.notNull(templateMode, "Template mode must be non-null");
        this.templateContent = templateContent;
        this.templateMode = templateMode;
    }
    @Override
    public void initialize() {
    }
    @Override
    public String getName() {
        return NAME;
    }
    @Override
    public Integer getOrder() {
        return ORDER;
    }
    @Override
    public TemplateResolution resolveTemplate(final TemplateProcessingParameters tpp) {
        String templateName = "CustomTemplate";
        String resourceName = "CustomResource";
        IResourceResolver resourceResolver = new FixedMemoryResourceResolver(templateContent);
        ITemplateResolutionValidity validity = new NonCacheableTemplateResolutionValidity();
        return new TemplateResolution(templateName, resourceName, resourceResolver, StandardCharsets.UTF_8.toString(),
                templateMode, validity);
    }
}
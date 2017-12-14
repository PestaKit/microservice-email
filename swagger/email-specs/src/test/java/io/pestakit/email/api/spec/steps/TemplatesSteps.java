package io.pestakit.email.api.spec.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.pestakit.email.ApiException;
import io.pestakit.email.ApiResponse;
import io.pestakit.email.api.DefaultApi;
import io.pestakit.email.api.dto.Tag;
import io.pestakit.email.api.dto.Template;
import io.pestakit.email.api.spec.helpers.Environment;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Loan Lassalle, Jérémie Zanone
 */
public class TemplatesSteps {

    private Tag tag;

    private Template template;
    private Long id;
    private String name;
    private List<String> tags;
    private List<String> parameters;
    private String body;

    private int numberOfTemplates;

    private Environment environment;
    private DefaultApi api;
    private ApiResponse lastApiResponse;
    private ApiException lastApiException;

    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public TemplatesSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^There is a template api$")
    public void thereIsATemplateApi() throws Throwable {
        assertNotNull(api);
    }

    @And("^I get the number of templates$")
    public void iGetTheNumberOfTemplates() throws Throwable {
        numberOfTemplates = api.getTemplates().size();
    }

    @Given("^I have a template object$")
    public void iHaveATemplateObject() throws Throwable {
        template = new Template();
        assertNotNull(template);
    }

    @And("^I set template's name$")
    public void iSetTemplateSName() throws Throwable {
        name = "Caco-Calo";
        template.setName(name);
        assertEquals(name, template.getName());
    }

    @And("^I set template's body")
    public void iSetTemplateSBody() throws Throwable {
        body = "<span th:text=\\\" 'Hello ' + ${title}  + ',\n" +
                "My name is ' + ${firstName} + '  ' + ${lastName} + '.\n'" +
                "\\\"></span>";
        template.setBody(body);
        assertEquals(body, template.getBody());
    }

    @When("^I POST it to the /templates endpoint$")
    public void iPOSTItToTheTemplatesEndpoint() throws Throwable {
        try {
            lastApiResponse = api.createTemplateWithHttpInfo(template);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code for template$")
    public void iReceiveAStatusCodeForTemplate(int arg0) throws Throwable {
        assertEquals(arg0, lastStatusCode);
    }

    @And("^The number of templates was increment of (\\d+)$")
    public void theNumberOfTemplatesWasIncrementOf(int arg0) throws Throwable {
        assertEquals(arg0, api.getTemplates().size() - numberOfTemplates);
    }

    @When("^I GET all templates to the /templates endpoint$")
    public void iGETAllTemplatesToTheTemplatesEndpoint() throws Throwable {
        try {
            lastApiResponse = api.getTemplatesWithHttpInfo();
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @And("^Response body should contain template data$")
    public void responseBodyShouldContainTemplateData() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @And("^The number of templates as same before$")
    public void theNumberOfTemplatesAsSameBefore() throws Throwable {
        assertEquals(api.getTemplates().size(), numberOfTemplates);
    }

    @And("^I get ID of last template")
    public void iGetIDOfLastTemplate() throws Throwable {
        id = (long) api.getTemplates().size();
        assertNotEquals(0, (long) id);
    }

    @And("^I get name of last template")
    public void iGetNameOfLastTemplate() throws Throwable {
        name = template.getName();
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }

    @And("^I get tags of last template")
    public void iGetTagsOfLastTemplate() throws Throwable {
        tags = template.getTags();
        assertTrue(tags.isEmpty());
    }

    @And("^I get parameters of last template")
    public void iGetParametersOfLastTemplate() throws Throwable {
        parameters = template.getParameters();
        assertTrue(parameters.isEmpty());
    }

    @And("^I get body of last template")
    public void iGetBodyOfLastTemplate() throws Throwable {
        body = template.getBody();
        assertNotNull(body);
        assertFalse(body.isEmpty());
    }

    @When("^I GET a template with ID to the /templates/id endpoint$")
    public void iGETATemplateWithIDToTheTemplatesIdEndpoint() throws Throwable {
        try {
            lastApiResponse = api.getTemplateWithHttpInfo(id);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @And("^I recover template in response body$")
    public Template iRecoverTemplateInResponseBody() throws Throwable {
        template = (Template) lastApiResponse.getData();
        assertNotNull(template);

        return template;
    }

    @And("^ID is same as last template")
    public void idIsSameAsLastTemplate() throws Throwable {
        assertEquals((long) id, api.getTemplates().size());
    }

    @And("^name is same as last template")
    public void nameIsSameAsLastTemplate() throws Throwable {
        assertEquals(name, template.getName());
    }

    @And("^tags are same as last template")
    public void tagsAreSameAsLastTemplate() throws Throwable {
        assertEquals(tags, template.getTags());
    }

    @And("^I change template's name$")
    public void iChangeTemplateSName() throws Throwable {
        name = "Rud-Bell";
        template.setName(name);
        assertEquals(name, template.getName());
    }

    @And("^I change template's body$")
    public void iChangeTemplateSBody() throws Throwable {
        body = "<span th:text=\\\" 'Hello ' + ${title}  + ',\n" +
                "My name is ' + ${firstName} + '  ' + ${lastName} + '.\n'" +
                "'Do you want to come to ' + ${place} + ' the ' + ${date} + ' ?\n'" +
                "\\\"></span>";
        template.setBody(body);
        assertEquals(body, template.getBody());
    }

    @And("^I have created a tag$")
    public void iHaveCreatedATag() throws Throwable {
        tag = new Tag();
        tag.setName("Advertising");
        tag.setTemplates(Collections.singletonList(template.getUrl()));
        api.createTag(tag);
        tag = api.getTags().get(api.getTags().size() - 1);
        assertNotNull(tag);
    }

    @And("^I change template's tags$")
    public void iChangeTemplateSTags() throws Throwable {
        tags = Collections.singletonList(template.getUrl());
        template.setTags(tags);
        assertEquals(tags, template.getTags());
    }

    @When("^I PUT a template with ID to the /templates/id endpoint$")
    public void iPUTATemplateWithIDToTheTemplatesIdEndpoint() throws Throwable {
        try {
            lastApiResponse = api.updateTemplateWithHttpInfo(String.valueOf(id), template);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @And("^Template's name has changed$")
    public void templateSNameHasChanged() throws Throwable {
        assertEquals(name, template.getName());
    }

    @And("^Template's tags has changed$")
    public void templateSTagsHasChanged() throws Throwable {
        assertEquals(tags, template.getTags());
    }

    @And("^Template's body has changed$")
    public void templateSBodyHasChanged() throws Throwable {
        assertEquals(body, template.getBody());
    }

    @When("^I DELETE a template with ID to the /templates/id endpoint$")
    public void iDELETEATemplateWithIDToTheTemplatesIdEndpoint() throws Throwable {
        try {
            lastApiResponse = api.deleteTemplateWithHttpInfo(id);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @And("^The number of templates was decremented of (\\d+)$")
    public void theNumberOfTemplatesWasDecrementedOf(int arg0) throws Throwable {
        assertEquals(arg0, numberOfTemplates - api.getTemplates().size());
    }
}

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

public class TagsStep {

    private Template template;

    private Tag tag;
    private Long id;
    private String name;
    private List<String> templates;

    private int numberOfTags;

    private Environment environment;
    private DefaultApi api;
    private ApiResponse lastApiResponse;
    private ApiException lastApiException;

    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public TagsStep(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^There is a tag api$")
    public void thereIsATagApi() throws Throwable {
        assertNotNull(api);
    }

    @And("^I get the number of tags")
    public void iGetTheNumberOfTags() throws Throwable {
        numberOfTags = api.getTags().size();
    }

    @Given("^I have a tag object$")
    public void iHaveATagObject() throws Throwable {
        tag = new Tag();
        assertNotNull(tag);
    }

    @And("^I set tag's name$")
    public void iSetTagSName() throws Throwable {
        name = "Food";
        tag.setName(name);
        assertEquals(name, tag.getName());
    }

    @When("^I POST it to the /tags endpoint$")
    public void iPOSTItToTheTagsEndpoint() throws Throwable {
        try {
            lastApiResponse = api.createTagWithHttpInfo(tag);
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

    @Then("^I receive a (\\d+) status code for tag$")
    public void iReceiveAStatusCodeForTag(int arg0) throws Throwable {
        assertEquals(arg0, lastStatusCode);
    }

    @And("^The number of tags was increment of (\\d+)$")
    public void theNumberOfTagsWasIncrementOf(int arg0) throws Throwable {
        assertEquals(arg0, api.getTags().size() - numberOfTags);
    }

    @When("^I GET all tags to the /tags endpoint$")
    public void iGETAllTagsToTheTagsEndpoint() throws Throwable {
        try {
            lastApiResponse = api.getTagsWithHttpInfo();
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

    @And("^Response body should contain tag data$")
    public void responseBodyShouldContainTagData() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }

    @And("^The number of tags as same before$")
    public void theNumberOfTagsAsSameBefore() throws Throwable {
        assertEquals(api.getTags().size(), numberOfTags);
    }

    @And("^I get ID of last tag$")
    public void iGetIDOfLastTag() throws Throwable {
        id = (long) api.getTags().size();
        assertNotEquals(0, (long) id);
    }

    @And("^I get name of last tag$")
    public void iGetNameOfLastTag() throws Throwable {
        name = tag.getName();
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }

    @And("^I get templates of last tag$")
    public void iGetTemplatesOfLastTag() throws Throwable {
        templates = tag.getTemplates();
        assertTrue(templates.isEmpty());
    }

    @When("^I GET a tag with ID to the /tags/id endpoint$")
    public void iGETATagWithIDToTheTagsIdEndpoint() throws Throwable {
        try {
            lastApiResponse = api.getTagWithHttpInfo(id);
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

    @And("^I recover tag in response body$")
    public Tag iRecoverTagInResponseBody() throws Throwable {
        tag = (Tag) lastApiResponse.getData();
        assertNotNull(tag);

        return tag;
    }

    @And("^ID is same as last tag$")
    public void idIsSameAsLastTag() throws Throwable {
        assertEquals((long) id, api.getTags().size());
    }

    @And("^name is same as last tag$")
    public void nameIsSameAsLastTag() throws Throwable {
        assertEquals(name, tag.getName());
    }

    @And("^templates are same as last tag$")
    public void templatesAreSameAsLastTag() throws Throwable {
        assertEquals(templates, tag.getTemplates());
    }

    @And("^I change tag's name$")
    public void iChangeTagSName() throws Throwable {
        name = "Drink";
        tag.setName(name);
        assertEquals(name, tag.getName());
    }

    @And("^I have created a template$")
    public void iHaveCreatedATemplate() throws Throwable {
        template = new Template();
        template.setName("Caco-Calo");
        template.setBody("<span th:text=\" 'Hello ' + ${title}  + ',\n" +
                "My name is ' + ${firstName} + '  ' + ${lastName} + '.\n'" +
                "\"></span>");
        api.createTemplate(template);
        template = api.getTemplate((long) api.getTemplates().size());
        assertNotNull(template);
    }

    @And("^I change tag's templates$")
    public void iChangeTagSTemplates() throws Throwable {
        templates = Collections.singletonList(template.getUrl());
        tag.setTemplates(templates);
        assertEquals(templates, tag.getTemplates());
    }

    @When("^I PUT a tag with ID to the /tags/id endpoint$")
    public void iPUTATagWithIDToTheTagsIdEndpoint() throws Throwable {
        try {
            lastApiResponse = api.updateTagWithHttpInfo(String.valueOf(id), tag);
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

    @And("^Tag's name has changed$")
    public void tagSNameHasChanged() throws Throwable {
        assertEquals(name, tag.getName());
    }

    @And("^Tag's templates has changed$")
    public void tagSTemplatesHasChanged() throws Throwable {
        assertEquals(templates, tag.getTemplates());
    }

    @When("^I DELETE a tag with ID to the /tags/id endpoint$")
    public void iDELETEATagWithIDToTheTagsIdEndpoint() throws Throwable {
        try {
            lastApiResponse = api.deleteTagWithHttpInfo(id);
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

    @And("^The number of tags was decremented of (\\d+)$")
    public void theNumberOfTagsWasDecrementedOf(int arg0) throws Throwable {
        assertEquals(arg0, numberOfTags - api.getTags().size());
    }
}

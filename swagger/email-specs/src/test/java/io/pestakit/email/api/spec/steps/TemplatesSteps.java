package io.pestakit.email.api.spec.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.pestakit.email.ApiException;
import io.pestakit.email.ApiResponse;
import io.pestakit.email.api.DefaultApi;
import io.pestakit.email.api.dto.Template;
import io.pestakit.email.api.spec.helpers.Environment;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TemplatesSteps {

    private Template template;
    private Long id;
    private String name;
    private List<String> tags;
    private String body;

    private int numberOfTemplates;
    private List<Long> tagsID;

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

//    @Given("^I have a template object$")
//    public void iHaveATemplateObject() throws Throwable {
//        template = new Template();
//        assertNotNull(template);
//    }
//
//    @And("^I set a name$")
//    public void iSetAName() throws Throwable {
//        name = "Caco-Calo";
//        template.setName(name);
//        assertEquals(name, template.getName());
//    }
//
//    @And("^I have created (\\d+) tags$")
//    public void iHaveCreatedTags(int arg0) throws Throwable {
//        int oldNumberOfTags = api.getTags().size();
//        tagsID = new ArrayList<>();
//
//        Tag tag = new Tag();
//        tag.setName("Food");
//        api.createTag(tag);
//        tagsID.add(oldNumberOfTags + 1L);
//
//        tag.setName("Drink");
//        api.createTag(tag);
//        tagsID.add(oldNumberOfTags + 2L);
//
//        tag.setName("Advertising");
//        api.createTag(tag);
//        tagsID.add(oldNumberOfTags + 3L);
//
//        assertEquals(arg0, api.getTags().size() - oldNumberOfTags);
//    }
//
//    @And("^I set tags$")
//    public void iSetTags() throws Throwable {
//        tags = Arrays.asList(api.getTag(tagsID.get(0)).getUrl(), api.getTag(tagsID.get(1)).getUrl());
//        template.setTags(tags);
//        assertEquals(tags, template.getTags());
//    }
//
//    @And("^I set a body$")
//    public void iSetABody() throws Throwable {
//        body = "<span th:text=\\\" 'Hello ' + ${title}  + ',\n" +
//                "My name is ' + ${firstName} + '  ' + ${lastName} + '.\n'" +
//                "\\\"></span>";
//        template.setBody(body);
//        assertEquals(body, template.getBody());
//    }
//
//    @When("^I POST it to the /templates endpoint$")
//    public void iPOSTItToTheTemplatesEndpoint() throws Throwable {
//        try {
//            lastApiResponse = api.createTemplateWithHttpInfo(template);
//            lastApiCallThrewException = false;
//            lastApiException = null;
//            lastStatusCode = lastApiResponse.getStatusCode();
//        } catch (ApiException e) {
//            lastApiCallThrewException = true;
//            lastApiResponse = null;
//            lastApiException = e;
//            lastStatusCode = lastApiException.getCode();
//        }
//    }
//
//    @And("^The number of templates was increment of (\\d+)$")
//    public void theNumberOfTemplatesWasIncrementOf(int arg0) throws Throwable {
//        assertEquals(arg0, api.getTemplates().size() - numberOfTemplates);
//    }
//
//    @When("^I GET all templates to the /templates endpoint$")
//    public void iGETAllTemplatesToTheTemplatesEndpoint() throws Throwable {
//        try {
//            lastApiResponse = api.getTemplatesWithHttpInfo();
//            lastApiCallThrewException = false;
//            lastApiException = null;
//            lastStatusCode = lastApiResponse.getStatusCode();
//        } catch (ApiException e) {
//            lastApiCallThrewException = true;
//            lastApiResponse = null;
//            lastApiException = e;
//            lastStatusCode = lastApiException.getCode();
//        }
//    }
//
//    @And("^Response body should contain all templates$")
//    public void responseBodyShouldContainAllTemplates() throws Throwable {
//        assertNotNull(lastApiResponse.getData());
//    }
//
//    @And("^The number of templates as same before$")
//    public void theNumberOfTemplatesAsSameBefore() throws Throwable {
//        assertEquals(api.getTemplates().size(), numberOfTemplates);
//    }
//
//    @Given("^I get ID of last template$")
//    public void iGetIDOfLastTemplate() throws Throwable {
//        id = (long) api.getTemplates().size();
//    }
//
//    @When("^I GET a template with ID to the /template/id endpoint$")
//    public void iGETATemplateWithIDToTheTemplateIdEndpoint() throws Throwable {
//        try {
//            lastApiResponse = api.getTemplateWithHttpInfo(id);
//            lastApiCallThrewException = false;
//            lastApiException = null;
//            lastStatusCode = lastApiResponse.getStatusCode();
//        } catch (ApiException e) {
//            lastApiCallThrewException = true;
//            lastApiResponse = null;
//            lastApiException = e;
//            lastStatusCode = lastApiException.getCode();
//        }
//    }
//
//    @And("^Response body should contain a template$")
//    public void responseBodyShouldContainATemplate() throws Throwable {
//        template = (Template) lastApiResponse.getData();
//        assertNotNull(template);
//    }
//
//    @And("^ID is same as before$")
//    public void idIsSameAsBefore() throws Throwable {
//        assertEquals((long) id, numberOfTemplates + 1);
//    }
//
//    @And("^name is same as before$")
//    public void nameIsSameAsBefore() throws Throwable {
//        assertEquals(name, template.getName());
//    }
//
//    @And("^tags are same as before$")
//    public void tagsAreSameAsBefore() throws Throwable {
//        assertEquals(tags, template.getTags());
//    }
//
//    @And("^body is same as before$")
//    public void bodyIsSameAsBefore() throws Throwable {
//        assertEquals(body, template.getBody());
//    }
//
//    @And("^parameters aren't null$")
//    public void parametersArenTNull() throws Throwable {
//        assertNotNull(template.getParameters());
//    }
//
//    @And("^parameters aren't empty$")
//    public void parametersArenTEmpty() throws Throwable {
//        assertFalse(template.getParameters().isEmpty());
//    }
//
//    @And("^I change the name$")
//    public void iChangeTheName() throws Throwable {
//        name = "Rud-Bell";
//        template.setName(name);
//        assertEquals(name, template.getName());
//    }
//
//    @And("^I change tags$")
//    public void iChangeTags() throws Throwable {
//        tags = Collections.singletonList(api.getTag(tagsID.get(2)).getUrl());
//        template.setTags(tags);
//        assertEquals(tags, template.getTags());
//    }
//
//    @And("^I change the body$")
//    public void iChangeTheBody() throws Throwable {
//        body = "<span th:text=\\\" 'Hello ' + ${title}  + ',\n" +
//                "My name is ' + ${firstName} + '  ' + ${lastName} + ', how are you today ?\n" +
//                "It's a beautiful day !'" +
//                "\\\"></span>";
//        template.setBody(body);
//        assertEquals(body, template.getBody());
//    }
//
//    @When("^I PUT a template with ID to the /templates/id endpoint$")
//    public void iPUTATemplateWithIDToTheTemplatesIdEndpoint() throws Throwable {
//        try {
//            lastApiResponse = api.updateTemplateWithHttpInfo(String.valueOf(id), template);
//            lastApiCallThrewException = false;
//            lastApiException = null;
//            lastStatusCode = lastApiResponse.getStatusCode();
//        } catch (ApiException e) {
//            lastApiCallThrewException = true;
//            lastApiResponse = null;
//            lastApiException = e;
//            lastStatusCode = lastApiException.getCode();
//        }
//    }
//
//    @When("^I DELETE a template with ID to the /templates/id endpoint$")
//    public void iDELETEATemplateWithIDToTheTemplatesIdEndpoint() throws Throwable {
//        try {
//            lastApiResponse = api.deleteTemplateWithHttpInfo(id);
//            lastApiCallThrewException = false;
//            lastApiException = null;
//            lastStatusCode = lastApiResponse.getStatusCode();
//        } catch (ApiException e) {
//            lastApiCallThrewException = true;
//            lastApiResponse = null;
//            lastApiException = e;
//            lastStatusCode = lastApiException.getCode();
//        }
//    }
//
//    @And("^The number of templates was decremented of (\\d+)$")
//    public void theNumberOfTemplatesWasDecrementedOf(int arg0) throws Throwable {
//        assertEquals(arg0, numberOfTemplates - api.getTemplates().size());
//    }
}

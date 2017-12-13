package io.pestakit.email.api.spec.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.pestakit.email.ApiException;
import io.pestakit.email.ApiResponse;
import io.pestakit.email.api.DefaultApi;
import io.pestakit.email.api.dto.Template;
import io.pestakit.email.api.spec.helpers.Environment;

import static org.junit.Assert.*;

public class TemplatesSteps {

    private Template template;

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

    @Given("^There is a Emails server$")
    public void there_is_a_Emails_server() throws Throwable {
        assertNotNull(api);
    }

    @And("^There is an empty database$")
    public void there_is_an_empty_database() throws Throwable {
        assertNull(lastApiResponse.getData());
    }

    @Given("^I have a template payload$")
    public void i_have_a_template_payload() throws Throwable {
        template = new Template();
        template.setBody("Bonjour, @Title @FirstName @LastName, comment allez vous ?");
        template.setName("TemplateBonjour");
    }

    @When("^I POST it to the /templates endpoint$")
    public void i_POST_it_to_the_templates_endpoint() throws Throwable {
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

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg0) throws Throwable {
        assertEquals(arg0, lastStatusCode);
    }

    @When("^I GET /templates$")
    public void i_GET_templates() throws Throwable {
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

    @And("^Response body should contain all templates$")
    public void response_body_should_contain_all_templates() throws Throwable {
        assertNotNull(lastApiResponse.getData());
    }
}

package io.pestakit.email.api.spec.steps;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.pestakit.email.ApiException;
import io.pestakit.email.ApiResponse;
import io.pestakit.email.api.dto.Template;
import io.pestakit.email.api.DefaultApi;

import io.pestakit.email.api.spec.helpers.Environment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class TemplatesSteps {

    Template template;

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


    @Given("^baseApi is http://localhost:(\\d+)/api$")
    public void baseapiIsHttpLocalhostApi(int arg0) throws Throwable {
        assertNotNull(api);
    }


    @When("^I GET /templates$")
    public void iGETTemplates() throws Throwable {
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
    public void iReceiveAStatusCode(int arg0) throws Throwable {
        assertEquals(201, arg0);
    }

    @Given("^I have a template payload$")
    public void iHaveATemplatePayload() throws Throwable {
        template = new io.pestakit.email.api.dto.Template();

    }
}

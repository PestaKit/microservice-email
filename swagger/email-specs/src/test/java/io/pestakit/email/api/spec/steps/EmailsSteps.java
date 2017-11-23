package io.pestakit.email.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.pestakit.email.ApiException;
import io.pestakit.email.ApiResponse;
import io.pestakit.email.api.DefaultApi;
import io.pestakit.email.api.dto.Email;
import io.pestakit.email.api.dto.Parameter;
import io.pestakit.email.api.dto.Template;
import io.pestakit.email.api.spec.helpers.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmailsSteps {

    private Template template;
    private Email email;

    private Environment environment;
    private DefaultApi api;
    private ApiResponse lastApiResponse;
    private ApiException lastApiException;

    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public EmailsSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^There is a email api$")
    public void thereIsAEmailApi() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a email object$")
    public void iHaveAEmailObject() throws Throwable {
        email = new Email();
    }

    @And("^I set a sender$")
    public void iSetASender() throws Throwable {
        email.setSender("jeremie.zanone@hotmail.com");
    }

    @And("^I set recipients$")
    public void iSetRecipientS() throws Throwable {
        List<String> recipients = new ArrayList<>();
        recipients.add("tano.iannetta@heig-vd.ch");
        email.setRecipients(recipients);
    }

    @And("^I set a blindCarbonCopy$")
    public void iSetABlindCarbonCopy() throws Throwable {
        List<String> copy = new ArrayList<>();
        copy.add("loan.lassale@heig-vd.ch");
        email.setBlindCarbonCopy(copy);
    }

    @And("^I set a subject$")
    public void iSetASubject() throws Throwable {
        email.setSubject("test cucumber");
    }

    @And("^I set parameters$")
    public void iSetParameters() throws Throwable {
        template = new Template();
        template.setBody("Bonjour @Title @FirstName @LastName, comment allez vous ?");
        template.setName("TemplateTestName");
        template.addParametersItem("@Title");
        template.addParametersItem("@FirstName");
        template.addParametersItem("@LastName");

        Parameter parameter = new Parameter();
        parameter.setKey("@Title");
        parameter.setValue("Monsieur");
        email.addParametersItem(parameter);

        Parameter parameter2 = new Parameter();
        parameter.setKey("@FirstName");
        parameter.setValue("Jérémie");
        email.addParametersItem(parameter2);

        Parameter parameter3 = new Parameter();
        parameter.setKey("@LastName");
        parameter.setValue("Zanone");
        email.addParametersItem(parameter3);
    }

    @When("^I POST it to the /email endpoint$")
    public void iPOSTItToTheEmailEndpoint() throws Throwable {
        try {
            lastApiResponse = api.createEmailWithHttpInfo(email);
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

    @Then("^I receive (\\d+) status code$")
    public void i_receive_status_code(int arg0) throws Throwable {
        assertEquals(arg0, lastStatusCode);
    }

    @And("^The recipient receive an email$")
    public void theRecipientReceiveAnEmail() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I have sent an email$")
    public void iHaveSentAnEmail() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}

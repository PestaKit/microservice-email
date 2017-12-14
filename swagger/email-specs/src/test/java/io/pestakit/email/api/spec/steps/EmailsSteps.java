package io.pestakit.email.api.spec.steps;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.pestakit.email.ApiException;
import io.pestakit.email.ApiResponse;
import io.pestakit.email.api.DefaultApi;
import io.pestakit.email.api.dto.EmailPrepared;
import io.pestakit.email.api.dto.Parameter;
import io.pestakit.email.api.dto.Template;
import io.pestakit.email.api.spec.helpers.Environment;
import org.subethamail.wiser.Wiser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class EmailsSteps {

    private Template template;
    private EmailPrepared email;
    int lastNbEmailsInAPI;
    int newNbEmailsInAPI;
    List<String> copy = new ArrayList<>();
    List<String> recipients = new ArrayList<>();
    private Wiser wiser;

    private Environment environment;
    private DefaultApi api;
    private ApiResponse lastApiResponse;
    private ApiException lastApiException;

    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public EmailsSteps(Environment environment) throws IOException {

        this.wiser = new Wiser();
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^There is a email api$")
    public void thereIsAEmailApi() throws Throwable {
        assertNotNull(api);
    }

    @And("^I created (\\d+) new templates$")
    public void iHaveTemplates(int arg0) throws Throwable {
        int oldNbTemplate = api.getTemplates().size();

        template = new Template();
        template.setBody("<span th:text\"= 'Bonjour ' + ${username}  + ',\n" +
                "Bienvenue sur notre plateforme d'échange'.\n'" +
                "\"></span>");
        template.setName("TemplateBonjour");
        api.createTemplate(template);

        template = new Template();
        template.setBody("<span th:text=\" 'Bonsoir ' + ${title}  + ',\n" + "My name is ' + ${firstName} + ' ' + ${lastName} + '.\n'" +
                "\"></span>");
        template.setName("TemplateBonsoir");
        api.createTemplate(template);

        template = new Template();
        template.setBody("<span th:text=\" 'Toute l'équipe vous souhaite de bonnes fêtes de fin d'année',\n\"></span>");
        template.setName("FinAnnée");
        api.createTemplate(template);

        int newNbTemplate = api.getTemplates().size();

        assertEquals(arg0, newNbTemplate - oldNbTemplate);

    }

    @Given("^I have a email object$")
    public void iHaveAEmailObject() throws Throwable {
        email = new EmailPrepared();
    }

    @And("^I set a sender$")
    public void iSetASender() throws Throwable {
        email.setSender("jeremie.zanone@hotmail.com");
    }

    @And("^I set recipients$")
    public void iSetRecipientS() throws Throwable {
        recipients.add("tano.iannetta@heig-vd.ch");
        email.setRecipients(recipients);
    }

    @And("^I set a blindCarbonCopy$")
    public void iSetABlindCarbonCopy() throws Throwable {
        copy.add("loan.lassale@heig-vd.ch");
        email.setBlindCarbonCopy(copy);
    }

    @And("^I set a subject$")
    public void iSetASubject() throws Throwable {
        email.setSubject("test cucumber");
    }

    @And("^I set template with parameters$")
    public void iSetParameters() throws Throwable {

        Parameter parameter = new Parameter();
        parameter.setKey("title");
        parameter.setValue("Monsieur");
        email.addParametersItem(parameter);

        parameter = new Parameter();
        parameter.setKey("firstName");
        parameter.setValue("Jérémie");
        email.addParametersItem(parameter);

        parameter = new Parameter();
        parameter.setKey("lastName");
        parameter.setValue("Zanone");
        email.addParametersItem(parameter);

        email.setTemplate(api.getTemplate(2l).getUrl());

    }

    @When("^I POST it to the /email endpoint$")
    public void iPOSTItToTheEmailEndpoint() throws Throwable {

        wiser.setPort(3030);
        wiser.setHostname("localhost");
        wiser.start();
        lastNbEmailsInAPI = api.getEmails().size();
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
        wiser.stop();
    }

    @Then("^I receive (\\d+) status code$")
    public void i_receive_status_code(int arg0) throws Throwable {
        assertEquals(arg0, lastStatusCode);
    }

    @And("^The recipient receive an email$")
    public void theRecipientReceiveAnEmail() throws Throwable {
        assertEquals(wiser.getMessages().size(), copy.size() + recipients.size());
    }

    @And("^I have sent an email$")
    public void iHaveSentAnEmail() throws Throwable {
        newNbEmailsInAPI = api.getEmails().size();
        assertEquals(1, newNbEmailsInAPI - lastNbEmailsInAPI);
    }

    @Then("^I get a (\\d+) status code$")
    public void iGetAStatusCode(int arg0) throws Throwable {
        assertEquals(arg0, lastStatusCode);
    }

    @And("^No email is send$")
    public void noEmailIsSend() throws Throwable {
        assertEquals(wiser.getMessages().size(), 0);
    }

    @And("^I set an invalid recipient$")
    public void iSetAnInvalidRecipient() throws Throwable {
        recipients.clear();
        recipients.add("I'm a fake address");
        email.setRecipients(recipients);
    }

    @And("^I set an invalid sender$")
    public void iSetAnInvalidSender() throws Throwable {
        email.setSender("ACH NEIN JE NE SUIS PAS UN SENDER VALIDE");
    }

    @And("^No invalid emails are stored$")
    public void noInvalidEmailsAreStored() throws Throwable {
        int idLastEmail = api.getEmails().size();
        assertTrue(api.getEmail(Long.valueOf(idLastEmail)).getSender().contains("@"));
        assertFalse(api.getEmail(Long.valueOf(idLastEmail)).getSender().contains(" "));
    }
}

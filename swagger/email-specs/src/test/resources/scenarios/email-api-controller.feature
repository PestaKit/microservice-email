Feature: Spring api controller email

  Background:
    Given There is a email api
    And I created 3 new templates

  Scenario: send a valid email
    Given I have a email object
    And I set a sender
    And I set recipients
    And I set a blindCarbonCopy
    And I set a subject
    And I set template with parameters
    When I POST it to the /email endpoint
    Then I receive 201 status code
    And The recipient receive an email
    And I have sent an email

  Scenario: send to an invalid recipient
    Given I have a email object
    And I set an invalid recipient
    And I set a sender
    And I set a subject
    And I set template with parameters
    When I POST it to the /email endpoint
    Then I get a 422 status code
    And No email is send

  Scenario: send from an invalid sender
    Given I have a email object
    And I set recipients
    And I set an invalid sender
    And I set a subject
    And I set template with parameters
    When I POST it to the /email endpoint
    Then I get a 422 status code
    And No email is send



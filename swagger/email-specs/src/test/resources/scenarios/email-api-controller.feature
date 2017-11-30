Feature: Spring api controller email

  Background:
    Given There is a email api

  Scenario: send an email
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

  Scenario: send an invalid email
    Given I send to an invalid mail recipient
    Then I get a 422 status code
    And No email is send



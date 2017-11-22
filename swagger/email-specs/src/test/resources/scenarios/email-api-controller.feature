Feature: Spring api controller email

  Background:
    Given There is a email api

  Scenario: send an email
    Given I have a email object
    And I set a sender
    And I set recipients
    And I set a blindCarbonCopy
    And I set a subject
    #template + parameters
    And I set a body
    When I POST it to the /email endpoint
    Then I receive 201 status code
    And The recipient receive an email
    And I have sent an email

  Scenario: send an email
    Given I write an invalid mail recipient



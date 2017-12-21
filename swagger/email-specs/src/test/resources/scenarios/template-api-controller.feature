Feature: Spring api controller template

  Background:
    Given There is a template api
    And I get the number of templates

  Scenario: Create a template
    Given I have a template object
    And I set template's name
    And I set template's body
    When I POST it to the /templates endpoint
    Then I receive a 201 status code for template
    And The number of templates was increment of 1

  Scenario: Get all templates
    When I GET all templates to the /templates endpoint
    Then I receive a 200 status code for template
    And Response body should contain template data
    And The number of templates as same before

  Scenario: Get a template
    Given I have a template object
    And I set template's name
    And I set template's body
    And I POST it to the /templates endpoint
    And I get ID of last template
    And I get name of last template
    And I get tags of last template
    And I get parameters of last template
    And I get body of last template
    When I GET a template with ID to the /templates/id endpoint
    Then I receive a 200 status code for template
    And Response body should contain template data
    And I recover template in response body
    And ID is same as last template
    And name is same as last template
    And tags are same as last template

  Scenario: Update a template
    Given I get ID of last template
    And I GET a template with ID to the /templates/id endpoint
    And Response body should contain template data
    And I recover template in response body
    And I change template's name
    And I have created a tag
    And I change template's tags
    And I change template's body
    When I PUT a template with ID to the /templates/id endpoint
    Then I receive a 200 status code for template
    And I get ID of last template
    And I GET a template with ID to the /templates/id endpoint
    And Template's name has changed
    And Template's tags has changed
    And Template's body has changed

  Scenario: Delete a template
    Given I get ID of last template
    When I DELETE a template with ID to the /templates/id endpoint
    Then I receive a 200 status code for template
    And The number of templates was decremented of 1
    When I GET a template with ID to the /templates/id endpoint
    Then I receive a 500 status code for template

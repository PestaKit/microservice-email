Feature: Spring api controller template

  Background:
    Given There is a Emails server

  Scenario: Create a template
    Given I have a template payload
    When I POST it to the /templates endpoint
    Then I receive a 201 status code

  Scenario: Create a template
    Given I have a template payload
    When I POST it to the /templates endpoint
    Then I receive a 201 status code

  Scenario: Get all templates
    When I GET /templates
    Then I receive a 200 status code
    And Response body should contain all templates

#  Scenario: Get a template
#    When I GET /template
#    Then I receive a 200 status code
#    And Response body should contain a template

  #Scenario: Delete a template
  #  When I DELETE a template to the /templates/$.id endpoint
  #  Then I receive a 200 status code
  #  And  The template doesn't exist anymore
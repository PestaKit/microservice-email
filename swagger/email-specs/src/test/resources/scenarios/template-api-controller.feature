Feature: Spring api controller template

  Background:
    Given There is a template api
    And I get the number of templates

#  Scenario: Create a template
#    Given I have a template object
#    And I set a name
#    And I have a tag object
#    And I set a name
#    And I POST it to the /tags endpoint
#    And I GET a tag with ID to the /tags/id endpoint
#    And I set tags
#    And I set a body
#    When I POST it to the /templates endpoint
#    Then I receive a 201 status code
#    And The number of templates was increment of 1
#
#  Scenario: Get all templates
#    When I GET all templates to the /templates endpoint
#    Then I receive a 200 status code
#    And Response body should contain all templates
#    And The number of templates as same before
#
#  Scenario: Get a template
#    Given I have a template object
#    And I set a name
#    And I have created 3 tags
#    And I set tags
#    And I set a body
#    When I POST it to the /templates endpoint
#    Then I receive a 201 status code
#    And The number of templates was increment of 1
#    And I get ID of last template
#    When I GET a template with ID to the /template/id endpoint
#    Then I receive a 200 status code
#    And Response body should contain a template
#    And ID is same as before
#    And name is same as before
#    And tags are same as before
#    And body is same as before
#    And parameters aren't null
#    And parameters aren't empty
#
#  Scenario: Update a template
#    Given I get ID of last template
#    And I GET a template with ID to the /template/id endpoint
#    And Response body should contain a template
#    And I change the name
#    And I have created 3 tags
#    And I change tags
#    And I change the body
#    When I PUT a template with ID to the /templates/id endpoint
#    Then I receive a 200 status code
#
#  Scenario: Delete a template
#    Given I get ID of last template
#    When I DELETE a template with ID to the /templates/id endpoint
#    Then I receive a 200 status code
#    And The number of templates was decremented of 1
#    When I GET a template with ID to the /template/id endpoint
#    Then I receive a 500 status code

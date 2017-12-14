Feature: Spring api controller tag

  Background:
    Given There is a tag api
    And I get the number of tags

  Scenario: Create a tag
    Given I have a tag object
    And I set a name
    When I POST it to the /tags endpoint
    Then I receive a 201 status code
    And The number of tags was increment of 1

  Scenario: Get all tags
    When I GET all tags to the /tags endpoint
    Then I receive a 200 status code
    And Response body should contain data
    And The number of tags as same before

  Scenario: Get a tag
    Given I have a tag object
    And I set a name
    And I POST it to the /tags endpoint
    And I get ID of last tag
    And I get name of last tag
    And I get templates of last tag
    When I GET a tag with ID to the /tags/id endpoint
    Then I receive a 200 status code
    And Response body should contain data
    And I recover tag in response body
    And ID is same as last tag
    And name is same as last tag
    And templates are same as last tag

  Scenario: Update a tag
    Given I get ID of last tag
    And I GET a tag with ID to the /tags/id endpoint
    And Response body should contain data
    And I recover tag in response body
    And I change the name
#    And I have a template object
#    And I set a name
#    And I have a tag object
#    And I set a name
#    And I POST it to the /tags endpoint
#    And I have created 3 templates
#    And I change templates
#    When I PUT a tag with ID to the /tags/id endpoint
#    Then I receive a 200 status code
#
#  Scenario: Delete a tag
#    Given I get ID of last tag
#    When I DELETE a tag with ID to the /tags/id endpoint
#    Then I receive a 200 status code
#    And The number of tags was decremented of 1
#    When I GET a tag with ID to the /tags/id endpoint
#    Then I receive a 500 status code

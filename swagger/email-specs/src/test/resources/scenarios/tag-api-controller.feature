Feature: Spring api controller tag

  Background:
    Given There is a tag api
    And I get the number of tags

  Scenario: Create a tag
    Given I have a tag object
    And I set tag's name
    When I POST it to the /tags endpoint
    Then I receive a 201 status code for tag
    And The number of tags was increment of 1

  Scenario: Get all tags
    When I GET all tags to the /tags endpoint
    Then I receive a 200 status code for tag
    And Response body should contain tag data
    And The number of tags as same before

  Scenario: Get a tag
    Given I have a tag object
    And I set tag's name
    And I POST it to the /tags endpoint
    And I get ID of last tag
    And I get name of last tag
    And I get templates of last tag
    When I GET a tag with ID to the /tags/id endpoint
    Then I receive a 200 status code for tag
    And Response body should contain tag data
    And I recover tag in response body
    And ID is same as last tag
    And name is same as last tag
    And templates are same as last tag

  Scenario: Update a tag
    Given I get ID of last tag
    And I GET a tag with ID to the /tags/id endpoint
    And Response body should contain tag data
    And I recover tag in response body
    And I change tag's name
    And I have created a template
    And I change tag's templates
    When I PUT a tag with ID to the /tags/id endpoint
    Then I receive a 200 status code for tag
    And I get ID of last tag
    And I GET a tag with ID to the /tags/id endpoint
    And Tag's name has changed
    And Tag's templates has changed

  Scenario: Delete a tag
    Given I get ID of last tag
    When I DELETE a tag with ID to the /tags/id endpoint
    Then I receive a 200 status code for tag
    And The number of tags was decremented of 1
    When I GET a tag with ID to the /tags/id endpoint
    Then I receive a 500 status code for tag

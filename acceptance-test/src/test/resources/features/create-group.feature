@create-group
Feature: User would like to add the group details

  Background:
    Given the following group details
      | name | description | createdBy  |
      | ITEC | ITEC        | sukeerti    |

  Scenario: User creating group
    When user creates a group
    Then the following group created
      | name | description | createdBy  |
      | ITEC | ITEC        | sukeerti    |

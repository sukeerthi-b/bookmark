@get-groups
Feature: User would like to get the groups

  Background:
    Given the following group information
      | name | description | createdBy  |
      | ITEC | ITEC        | sukeerti    |

  Scenario: User request for group information
    When user requests for group details
    Then user gets group information as
      | name | description | createdBy  |
      | ITEC | ITEC        | sukeerti   |

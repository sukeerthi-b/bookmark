@create-bookmark
Feature: User would like to add the bookmark to specific group

  Scenario: User creating bookmark
    Given the following bookmark details
      | createdBy | actualUrl         | title  | source |
      | sukeerti  | https://google.com| google | SG     |
    When user creates a bookmark
    Then the following bookmark created
      | createdBy | actualUrl         | title  | source | shortUrl                |
      | sukeerti  | https://google.com| google | SG     | <Random generated value> |

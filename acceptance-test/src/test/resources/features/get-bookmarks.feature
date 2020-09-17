@get-bookmarks
Feature: User would like to get the bookmarks

  Background:
    Given the following bookmark information
      | createdBy | actualUrl         | title  | source |
      | sukeerti  | https://google.com| google | SG     |

  Scenario: User request for bookmark information
    When user requests for bookmark details
    Then user gets bookmark information as
      | createdBy | actualUrl         | title  | source | shortUrl     |
      | sukeerti  | https://google.com| google | SG     | https://g.com |

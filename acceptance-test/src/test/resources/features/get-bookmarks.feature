Feature: User would like to get the bookmarks

  Background:
    Given the following bookmark information
      | bookmrak_tecid | actual_url         | title  | source | short_url     |
      | 1              | https://google.com | google | SG     | https://g.com |

  Scenario: User request for bookmark information
    When user requests for bookmark details
    Then user gets bookmark information as
      | bookmrak_tecid | actual_url         | title  | source | short_url     |
      | 1              | https://google.com | google | SG     | https://g.com |

Feature: Example
  Scenario: DuckDuckGo search
    Given that the user is on the DuckDuckGo page
    When the search phrase "OpenBSD" is entered
    Then results for "OpenBSD" are shown
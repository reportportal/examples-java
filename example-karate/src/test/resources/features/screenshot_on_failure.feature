Feature: Demonstrate screenshot capturing on a test failure

  Background:
    * configure driver = { type: 'chrome' }
    * def expectedTitle = java.lang.System.getenv('RP_EXPECTED_TITLE');

  Scenario: I report scenario with a screenshot on failure
    Given driver 'https://www.example.com'
    When def actualTitle = driver.title
    Then match actualTitle == expectedTitle

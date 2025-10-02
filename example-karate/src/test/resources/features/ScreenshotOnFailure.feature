Feature: Demonstrate screenshot capturing on a test failure

  Background:
    * configure driver = { type: 'chrome' }

  Scenario: I report scenario with a screenshot on failure
    Given driver 'https://www.example.com'
    When var expectedTitle = java.lang.System.getenv('RP_EXPECTED_TITLE');
    Then match driver.title == expectedTitle

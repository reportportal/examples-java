Feature: Demonstrate screenshot capturing

  Background:
    * configure driver = { type: 'chrome' }

  Scenario: I report scenario with a screenshot
    Given driver 'https://www.example.com'
    When def bytes = screenshot(false)
    Then karate.embed(bytes, 'image/png')

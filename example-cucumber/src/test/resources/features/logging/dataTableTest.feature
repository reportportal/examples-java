Feature: DataTableTest

  Scenario: Searching for glasses
    Given I have the following items in bag
      | phone  |
      | laptop |
    When I search for glasses
    Then I find 0 items

  Scenario:  Searching for phone
    Given I have the following items in bag
      | phone  |
      | laptop |
    When I search for phone
    Then I find 1 items
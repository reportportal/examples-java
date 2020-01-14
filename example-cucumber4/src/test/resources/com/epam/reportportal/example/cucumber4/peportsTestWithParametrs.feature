Feature: Test with parameters

  Scenario Outline: Test with different parameters
    Given It is test with parameters
    Then I emit number 15 on level info

    Examples:
      | parameters  |
      | 123         |
      | 12345       |
      | 12312345678 |

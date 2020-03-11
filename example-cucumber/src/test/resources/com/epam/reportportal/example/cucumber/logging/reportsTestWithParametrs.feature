Feature: Test with parameters

  Scenario Outline: Test with different parameters
    Given It is test with parameters
    When I have <str> parameter
    Then I emit number <parameters> on level info

    Examples:
      | str    | parameters  |
      | first  | 123         |
      | second | 12345       |
      | third  | 12312345678 |

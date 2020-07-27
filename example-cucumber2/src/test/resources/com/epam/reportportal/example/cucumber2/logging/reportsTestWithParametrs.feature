Feature: Test with parameters

  Scenario Outline: Test with different parameters
    Given It is test with parameters
    When I have parameter <str>
    Then I emit number <parameters> on level info

    Examples:
      | str    | parameters  |
      | first  | 123         |
      | second | 12345       |
      | third  | 12312345678 |

  Scenario Outline: Test with few parameter in method
    Given I have <number> <item> in my pocket
    When I eat one
    Then I have <result> in my pocket

    Examples:
      | number | item      | result |
      | 100    | apples    | 99     |
      | 3      | cucumbers | 2      |
      | 1      | cake      | 0      |

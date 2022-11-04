Scenario: Test with different parameters
Given It is test with parameters
Then I emit number <number> on level info
Examples:
      | number      |
      | 123         |
      | 12345       |
      | 12312345678 |

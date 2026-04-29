Feature: verify basic HTTP request

  Scenario: Verify HTTP request
    Given url 'https://example.com'
    And header Content-Type = 'application/json'
    And path 'api/test'
    And request
    """
    {
      username: 'user',
      password: 'password',
      grant_type: 'password'
    }
    """
    When method post
    Then status 405

Feature: Demonstrate image attachment

  Scenario: I attach image to report
    When def bytes = karate.read('classpath:pug/lucky.png')
    Then karate.embed(bytes, 'image/png')

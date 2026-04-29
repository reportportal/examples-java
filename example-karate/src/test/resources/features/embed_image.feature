Feature: Demonstrate image attachment

  Scenario: I attach image to report
    When def bytes = karate.read('classpath:pug/lucky.jpg')
    Then karate.embed(bytes, 'image/jpeg')

Feature: Board Page

  @drag&drop
  Scenario: create and drag&drop card
    Given user is connected to "https://chewie.one2team.com/"
    And user is on board page
    When user creates a new card
    And user drag&drop card
    Then card is correctly dragged and dropped
@home
Feature: Home

  @shortcuts
  Scenario Outline: Click on button <button>
    Given user is connected to "https://chewie.one2team.com/"
    When user clicks on button "<button>" with "<css>"
    Then user is redirected to right page "<page>"
    Examples:
      | button       | css                | page             |
      | site web     | > div:nth-child(1) | one2team.com     |
      | google drive | > div:nth-child(2) | drive.google.com |

  @tabs
  Scenario Outline: <action> tab <tab_name>
    Given user is connected to "https://chewie.one2team.com/"
    When I "<action>" tab "<tab_index>"
    Then items inside tab are "<tab_content_state>"
    Examples:
      | action   | tab_name | tab_index | tab_content_state |
      | expand   | favorite | 1         | shown             |
      | expand   | overview | 2         | shown             |
      | collapse | favorite | 1         | hidden            |
      | collapse | overview | 2         | hidden            |

  @Logout
  Scenario: Logout
    Given user is connected to "https://chewie.one2team.com/"
    When user disconnects from platform
    Then user is redirected to Login page
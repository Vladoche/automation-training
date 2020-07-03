Feature: Home

  Scenario Outline: Click on button <button>
    Given user is connected to "https://chewie.one2team.com/"
    When user clicks on button "<button>" with "<css>"
    Then user is redirected to right page "<page>"
    Examples:
      | button       | css                | page             |
      | site web     | > div:nth-child(1) | one2team.com     |
      | google drive | > div:nth-child(2) | drive.google.com |


  Scenario: Logout
    Given user is connected to "https://chewie.one2team.com/"
    When user disconnects from platform
    Then user is redirected to Login page

  @this
  Scenario Outline: Tab <tab_name>
    Given user is connected to "https://chewie.one2team.com/"
    When I collapse "<tab_index>"
    Then items inside tab are hidden
    Examples:
      | tab_name | tab_index |
      | favorite | 1         |
      | overview | 2         |
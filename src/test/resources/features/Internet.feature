@theinternet
Feature: The internet

  @add_remove
  Scenario: add remove elements
    Given I am on page "add_remove_elements/"
    When I click on button "Add Element"
    Then A button "Delete" is added to the page

  @pop_auth
  Scenario: basic popup auth - pass
    Given I am on page "basic_auth"
    When I login with username and password "admin:admin"
    Then I have "successful" login message

  @pop_auth @fail
  Scenario: basic popup auth - fail
    Given I am on page "basic_auth"
    When I login with incorrect username and password
    Then I have "failed" login message

  @form_auth @deprecated
  Scenario: basic form auth - Login pass
    Given I am on page "login"
    When I enter login "tomsmith"
    And password "SuperSecretPassword!"
    And click on button Login
    Then login is successful

  @form_auth @deprecated
  Scenario: basic form auth - fail wrong username
    Given I am on page "login"
    When I enter login "anass"
    And password "SuperSecretPassword!"
    And click on button Login
    Then login is failed with wrong "username" message

  @form_auth @deprecated
  Scenario: basic form auth - fail wrong password
    Given I am on page "login"
    When I enter login "tomsmith"
    And password "faux!"
    And click on button Login
    Then login is failed with wrong "password" message

  @form_auth
  Scenario Template: <title>
    Given I am on page "login"
    When I enter login "<username>"
    And password "<password>"
    And click on button Login
    Then login is "<login_status>" with "<message>"
    Examples:
      | title                                            | username | password             | login_status | message                        |
      | template - basic form auth - Login pass          | tomsmith | SuperSecretPassword! | successful   | You logged into a secure area! |
      | template - basic form auth - fail wrong username | anass    | SuperSecretPassword! | failed       | Your username is invalid!      |
      | template - basic form auth - fail wrong password | tomsmith | wrong_pass           | failed       | Your password is invalid!      |

  Scenario: Identify frames
    Given I am on page "nested_frames"
    When I identify all webpage frames

  @frame
  Scenario Template: Switch to frame
    Given I am on page "nested_frames"
    When I switch to frame "<frame>"
    Examples:
      | frame        |
      | frame-left   |
      | frame-middle |
      | frame-right  |
      | frame-bottom |



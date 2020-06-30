@livecoding
Feature: The internet

  Scenario: add remove elements
    Given I am on page "https://the-internet.herokuapp.com/add_remove_elements/"
    When I click on button "Add Element"
    Then A button "Delete" is added to the page

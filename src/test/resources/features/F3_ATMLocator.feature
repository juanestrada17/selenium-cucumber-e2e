# Author: Juan
# Date: Oct. 23, 2025
@NearestATM @RegressionTest
Feature: As a user I want to locate the nearest ATM to an specific address so that I can plan ahead on a visit

  Background:
    Given User launches the application
    And User should be at the homepage
    And User accepts the privacy settings

  Scenario:
    When User clicks search address button
    Then User should see a "Where are you located?" message
    And User should see a "Share Your Location" button

  Scenario Outline:
    When User inputs an address "<address>"
    And User clicks search address button
    Then User should be able to see 10 closest ATM or branch suggestions
    Then User should be able to see the cards
    And User should be able to see "<atmbranch>"

    Examples:
      | address      | atmbranch             |
      | 130 Adelaide | Adelaide & York       |
      | 222 Queen St | Petro-Canada          |
      | 720 King St  | RBC The Well On Front |
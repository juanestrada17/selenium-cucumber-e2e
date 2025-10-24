# Author: Juan
# Date: Oct. 23, 2025
@RRSP @RegressionTest
Feature: As a user I want to calculate how much money I can save in a registered retirement savings
  plan so that reach my retirement goals

  Background:
    Given User launches the application
    And User should be at the homepage
    And User accepts the privacy settings
    And User sees the Investments tab
    And User hovers over Investments tab
    And User clicks RRSP Calculator link
    And User should be at the rrsp calculator page
    And User should see the Calculator fields

  @RateFieldTooltip
  Scenario: User should be able to click on rate of return tooltip to get a message explaining what to input
    Given User can see the tooltip next to the rate field
    When User clicks the tooltip for rate field
    And User can see tooltip content
    Then User should see a message "This is how much growth you expect to see in your account between now and retirement."

  @IncorrectReturnRate
  Scenario Outline: User should get an error when entering rate of return lower than 1% or higher than 1000%
    When User inputs an invalid rate of return "<rate>"
    Then User should be able to see the message "Please select a rate between 1% and 1000%"
    Examples:
      | rate |
      | -1   |
      | 0    |
      | 1001 |
      | 1002 |

  @TotalSavings
  Scenario Outline: User should be able to calculate total savings given and shown a savings tip when
  calculating with a frequency higher than Bi-weekly
    When User inputs the years "<years>" to retire
    And User inputs the ongoing contribution "<amount>"
    And User selects the contribution "<frequency>"
    And User inputs the "<rate>" of return
    And User clicks calculate button
    Then User should see the total "<savings>"
    Then User should see a tip with potential savings "<potentialsavings>" and recommended contribution amount "<tipcontribution>"

    Examples:
      | years | amount | frequency | rate | savings     | potentialsavings | tipcontribution |
      | 20    | 300    | Monthly   | 5    | $123,310.10 | $10,512          | $150            |
      | 1     | 25     | Monthly   | 1    | $301.38     | $25              | $13             |
      | 50    | 25     | Quarterly | 1    | $6,476.93   | $549             | $4              |



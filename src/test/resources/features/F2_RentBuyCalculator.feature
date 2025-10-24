# Author: Juan
# Date: Oct. 23, 2025
@RentBuyCalculator @RegressionTest
Feature: As a user I want to calculate how much money I can save in a registered retirement savings
  plan so that reach my retirement goals

  Background:
    Given User launches the application
    And User should be at the homepage
    And User accepts the privacy settings
    And User sees the Mortgages tab
    And User hovers over Mortgages tab
    And User clicks Rent vs Buy Calculator
    And User should be at the Rent vs Buy Calculator page
    And User should see the calculator fields

  Scenario: User should be able to click on the amortization period and get a clear message of what it means
    When User clicks the amortization period tooltip
    Then User should be able to see "Most people choose a 25-year amortization period" in the period message

  Scenario: User should be able to click on the down payment tooltip and get a clear message of what it means
    When User clicks the down payment tooltip
    Then User should be able to see "In order to be approved for a mortgage, you will need at least 5% of the purchase price" in the down payment

  Scenario Outline: User should be able to see an error message if monthly rent is not provided
    When User inputs monthly rent "<rent>"
    Then User shouldn't be able to click on the calculate button and it should be disabled
    Then User should see an error message "Please enter your monthly rent."
    Examples:
      | rent |
      | 0    |
      |      |

  Scenario Outline: User should be able to see the mortgage (loan) amount when selecting 5%, 10% and 20% down payment for a
  given purchase price.
    When User inputs monthly rent "<rent>"
    And User inputs interest rate "<interest>" percent
    And User selects the amortization "<amortization>" period
    And User selects the down payment "<payment>" percentage
    And User clicks calculate button in rent vs buy calculator
    Then User should be able to see 3 cards for 5%, 10% and 20% down payment
    And User should be able to see the total mortgage "<fiveprice>" "<tenprice>" "<twentyprice>"

    Examples:
      | rent | interest | amortization | payment | fiveprice | tenprice | twentyprice |
      | 800  | 4.590    | 30 years     | 5%      | $158,935  | $169,230 | $196,285    |
      | 1200 | 5        | 20 years     | 10%     | $184,832  | $196,803 | $228,267    |
      | 2000 | 6        | 10 years     | 20%     | $182,943  | $194,792 | $225,935    |
package stepdefinitions;

import data.DataFile;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F1_RRSPCalculator {
    @Given("User launches the application")
    public void user_launches_the_application() throws InterruptedException {
        Hooks.driver.navigate().to(DataFile.homePageUrl);
    }

    @Given("User should be at the homepage")
    public void user_should_be_at_the_homepage() throws InterruptedException {
        Thread.sleep(1000);
        Hooks.homePage.verifyWelcomeText();
        Hooks.homePage.verifyPageUrl();
    }

    @And("User accepts the privacy settings")
    public void userAcceptsThePrivacySettings() {
        Hooks.cookiesPopup.clickAcceptAllCookies();
    }

    @Given("User sees the Investments tab")
    public void user_sees_the_investments_tab() throws InterruptedException {
        Hooks.homePage.verifyInvestmentsLink();
    }

    @When("User hovers over Investments tab")
    public void user_hovers_over_investments_tab() throws InterruptedException {
        Hooks.homePage.hoverInvestmentsLink();
    }

    @When("User clicks RRSP Calculator link")
    public void user_clicks_rrsp_calculator_link() throws InterruptedException {
        Hooks.homePage.clickRRSPCalculatorLnk();
    }

    @Then("User should be at the rrsp calculator page")
    public void user_should_be_at_the_rrsp_calculator_page() throws InterruptedException {
        Hooks.rsspCalculatorPage.verifyPageTitle();
    }

    @Given("User should see the Calculator fields")
    public void user_should_see_the_calculator_fields() throws InterruptedException {
        Hooks.rsspCalculatorPage.verifyVisibleFields();
        Thread.sleep(1000);
    }

    @When("User inputs the years {string} to retire")
    public void user_inputs_the_years_to_retire(String years) throws InterruptedException {
        Hooks.rsspCalculatorPage.inputRetireYears(years);
        Thread.sleep(1000);
    }

    @When("User inputs the ongoing contribution {string}")
    public void user_inputs_the_ongoing_contribution_amount(String contribution) throws InterruptedException {
        Hooks.rsspCalculatorPage.inputContributionAmount(contribution);
        Thread.sleep(1000);
    }

    @When("User selects the contribution {string}")
    public void user_selects_the_contribution_frequency(String contribution) throws InterruptedException {
        Hooks.rsspCalculatorPage.selectContributionFrequency(contribution);
        Thread.sleep(1000);
    }

    @When("User inputs the {string} of return")
    public void user_inputs_the_rate_of_return(String rate) throws InterruptedException {
        Hooks.rsspCalculatorPage.inputRateOfReturn(rate);
        Thread.sleep(1000);
    }

    @And("User clicks calculate button")
    public void userClicksCalculateButton() throws InterruptedException {
        Hooks.rsspCalculatorPage.clickCalculateBtn();
        Thread.sleep(1000);
    }

    @Then("User should see the total {string}")
    public void userShouldSeeTheTotal(String savings) {
        Hooks.rsspCalculatorPage.verifySavingsAmount(savings);
    }

    @Then("User should see a tip with potential savings {string} and recommended contribution amount {string}")
    public void userShouldSeeATipWithPotentialSavingsAndReccomendedContributionAmount(String savingsAmount, String contributionAmount) {
        Hooks.rsspCalculatorPage.verifySavingsDiff(savingsAmount, contributionAmount);
    }

    @Given("User can see the tooltip next to the rate field")
    public void userCanSeeTheTooltipNextToTheRateField() {
        Hooks.rsspCalculatorPage.verifyRateTooltip();
    }

    @When("User clicks the tooltip for rate field")
    public void userClicksTheTooltipForRateField() {
        Hooks.rsspCalculatorPage.clickRateOfReturnTooltip();
    }

    @Then("User should see a message {string}")
    public void userShouldSeeAMessage(String message) {
        Hooks.rsspCalculatorPage.verifyRateTooltipContent(message);
    }


    @When("User inputs an invalid rate of return {string}")
    public void userInputsAnInvalidRateOfReturn(String invalidRate) {
        Hooks.rsspCalculatorPage.inputRateOfReturn(invalidRate);
    }

    @Then("User should be able to see the message {string}")
    public void userShouldBeAbleToSeeTheMessage(String message) {
        Hooks.rsspCalculatorPage.verifyRateErrorMsgVisibility();
        Hooks.rsspCalculatorPage.verifyRateErrorMsg(message);
    }
}

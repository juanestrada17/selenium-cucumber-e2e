package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F2_RentBuyCalculator {
    @Given("User sees the Mortgages tab")
    public void user_sees_the_mortgages_tab() {
        Hooks.homePage.verifyMortgagesLink();
    }

    @Given("User hovers over Mortgages tab")
    public void user_hovers_over_mortgages_tab() {
        Hooks.homePage.hoverMortgagesLink();
    }

    @Given("User clicks Rent vs Buy Calculator")
    public void user_clicks_rent_vs_buy_calculator() {
        Hooks.homePage.clickRentBuyCalculatorLink();
    }

    @Given("User should be at the Rent vs Buy Calculator page")
    public void user_should_be_at_the_rent_vs_buy_calculator_page() {
        Hooks.rentBuyCalculatorPage.verifyPageTitle();
    }

    @Given("User should see the calculator fields")
    public void user_should_see_the_calculator_fields() {
        Hooks.rentBuyCalculatorPage.verifyFormFields();
    }

    @When("User inputs monthly rent {string}")
    public void user_inputs_monthly_rent(String rent) {
        Hooks.rentBuyCalculatorPage.inputRentAmount(rent);
    }

    @When("User inputs interest rate {string} percent")
    public void user_inputs_interest_rate_percent(String rate) {
        Hooks.rentBuyCalculatorPage.inputInterestRate(rate);
    }

    @When("User selects the amortization {string} period")
    public void user_selects_the_amortization_period(String amortization) {
        Hooks.rentBuyCalculatorPage.selectAmortizationPeriod(amortization);
    }

    @When("User selects the down payment {string} percentage")
    public void user_selects_the_down_payment_percentage(String payment) {

        Hooks.rentBuyCalculatorPage.selectDownPayment(payment);
    }

    @When("User clicks calculate button in rent vs buy calculator")
    public void user_clicks_calculate_button_in_rent_vs_buy_calculator() {
        Hooks.rentBuyCalculatorPage.clickCalculateBtn();
    }

    @Then("User should be able to see 3 cards for 5%, 10% and 20% down payment")
    public void user_should_be_able_to_see_cards_for_and_down_payment() {
        Hooks.rentBuyCalculatorPage.verifyCardsAmount();
    }


    @And("User should be able to see the total mortgage {string} {string} {string}")
    public void userShouldBeAbleToSeeTheTotalMortgage(String five, String ten, String twenty) {
        Hooks.rentBuyCalculatorPage.verifyMortgageCards(five, ten, twenty);
    }

    @When("User clicks the amortization period tooltip")
    public void userClicksTheAmortizationPeriodTooltip() {
        Hooks.rentBuyCalculatorPage.clickAmortizationTooltip();
    }

    @Then("User should be able to see {string} in the period message")
    public void userShouldBeAbleToSee(String message) {
        Hooks.rentBuyCalculatorPage.verifyAmortizationTooltipMsg(message);
    }

    @When("User clicks the down payment tooltip")
    public void userClicksTheDownPaymentTooltip() {
        Hooks.rentBuyCalculatorPage.clickPaymentTooltip();
    }

    @Then("User should be able to see {string} in the down payment")
    public void userShouldBeAbleToSeeInTheDownPayment(String message) {
        Hooks.rentBuyCalculatorPage.verifyPaymentTooltipMsg(message);
    }

    @Then("User should see an error message {string}")
    public void userShouldSeeAnErrorMessage(String message) {
        Hooks.rentBuyCalculatorPage.verifyRentValidationMessage(message);
    }

    @Then("User shouldn't be able to click on the calculate button and it should be disabled")
    public void userShouldNotTBeAbleToClickOnTheCalculateButtonAndItShouldBeDisabled() {
        Hooks.rentBuyCalculatorPage.verifyCalculateButtonDisabled();
    }
}

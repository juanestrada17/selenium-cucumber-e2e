package stepdefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class F3_ATMLocator {
    @When("User inputs an address {string}")
    public void user_inputs_an_address(String address) {
        Hooks.homePage.inputAddress(address);
    }

    @When("User clicks search address button")
    public void user_clicks_search_address_button(){
        Hooks.homePage.clickATMLocatorButton();
    }

    @Then("User should be able to see {int} closest ATM or branch suggestions")
    public void user_should_be_able_to_see_closest_atm_or_branch_suggestions(int resultAmount) {
        Hooks.atmLocator.verifyCardElementsAmount(resultAmount);
    }

    @Then("User should be able to see the cards")
    public void userShouldBeAbleToSeeTheCards() {
        Hooks.atmLocator.verifyVisibleSideBar();
    }

    @And("User should be able to see {string}")
    public void userShouldBeAbleToSee(String address) {
        Hooks.atmLocator.verifyFirstCardAddress(address);
    }

    @Then("User should see a Where are you located? {string} message")
    public void userShouldSeeAWhereMessage(String message) {
       Hooks.atmLocator.verifyPageTitle(message);
    }

    @And("User should see a Share Your Location {string} button")
    public void userShouldSeeAShareLocationButton(String message) {
        Hooks.atmLocator.verifyShareLocationBtn(message);
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RSSPCalculatorPage {
    WebDriver driver;

    public RSSPCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(tagName = "h1")
    private WebElement pageTitle;
    @FindBy(className = "grid-one-third")
    private List<WebElement> calculatorFields;
    @FindBy(xpath = "//input[@id='retire']")
    private WebElement retireYears;
    @FindBy(xpath = "//input[@id='contribution']")
    private WebElement contributionAmount;
    @FindBy(xpath = "//select[@id='frequency']")
    private WebElement contributionFrequency;
    @FindBy(xpath = "//input[@id='rate']")
    private WebElement rateOfReturn;
    @FindBy(xpath = "//a[@id='calculate']")
    private WebElement calculateBtn;
    @FindBy(xpath = "//span[@class='results-save']")
    private WebElement totalSavingsAmount;
    @FindBy(xpath = "//span[@class='biweekly-tip-diff']")
    private WebElement savingsTipDiffTxt;
    @FindBy(xpath = "//span[@class='biweekly-tip-contribution']")
    private WebElement tipDiffTxt;
    // New Vals
    @FindBy(xpath = "//button[@data-id='rate-tooltip']")
    private WebElement rateOfReturnTooltip;
    @FindBy(xpath = "//div[@id='rate-tooltip-content']")
    private WebElement rateTooltipContent;
    @FindBy(id = "error-rate")
    private WebElement rateOfReturnErrorMsg;

    // Methods
    public void verifyPageTitle() {
        assertTrue(pageTitle.isDisplayed());
    }

    public void verifyVisibleFields() {
        for (WebElement field : calculatorFields) {
            assertTrue(field.isDisplayed());
        }
    }

    public void inputRetireYears(String years) {
        retireYears.clear();
        retireYears.sendKeys(years);
    }

    public void inputContributionAmount(String amount) {
        contributionAmount.clear();
        contributionAmount.sendKeys(amount);
    }

    public void selectContributionFrequency(String frequency) {
        Select contribution = new Select(contributionFrequency);
        contribution.selectByVisibleText(frequency);
    }

    public void inputRateOfReturn(String rate) {
        rateOfReturn.clear();
        rateOfReturn.sendKeys(rate);
    }

    public void clickCalculateBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.elementToBeClickable(calculateBtn)).click();
    }

    public void verifySavingsAmount(String amount) {
        assertEquals(amount, totalSavingsAmount.getText());
    }

    public void verifySavingsDiff(String savingsAmount, String contributionAmount) {
        assertEquals(savingsAmount, savingsTipDiffTxt.getText());
        assertEquals(contributionAmount, tipDiffTxt.getText());
    }

    public void clickRateOfReturnTooltip() {
        rateOfReturnTooltip.click();
    }

    public void verifyRateTooltip() {
        rateOfReturnTooltip.isDisplayed();
    }

    public void verifyRateTooltipContent(String expectedContent) {
        assertTrue(rateTooltipContent.isDisplayed());
        assertTrue(expectedContent, rateTooltipContent.getText().contains(expectedContent));
    }

    public void verifyRateErrorMsgVisibility() {
        rateOfReturnErrorMsg.isDisplayed();
    }

    public void verifyRateErrorMsg(String expectedError) {
        assertTrue(expectedError, rateOfReturnErrorMsg.getText().contains(expectedError));
    }
}

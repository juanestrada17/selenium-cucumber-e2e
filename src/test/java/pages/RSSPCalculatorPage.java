package pages;

import data.DataFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RSSPCalculatorPage extends BasePage {


    @FindBy(tagName = "h1")
    private WebElement pageTitle;
    @FindBy(xpath = "//div[@class='grid-one-third']//label")
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
    @FindBy(xpath = "//button[@data-id='rate-tooltip']")
    private WebElement rateOfReturnTooltip;
    @FindBy(xpath = "//div[@id='rate-tooltip-content']")
    private WebElement rateTooltipContent;
    @FindBy(id = "error-rate")
    private WebElement rateOfReturnErrorMsg;

    public RSSPCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public void verifyPageTitle() {
        assertTrue(pageTitle.isDisplayed());
    }

    public void verifyVisibleFields() {
        assertEquals(DataFile.rsspCalculatorNumber, calculatorFields.size());
        for (WebElement field : calculatorFields) {
            assertTrue(field.isDisplayed());
        }
    }

    public void inputRetireYears(String years) {
        assertTrue(retireYears.isDisplayed());
        retireYears.clear();
        retireYears.sendKeys(years);
    }

    public void inputContributionAmount(String amount) {
        assertTrue(contributionAmount.isDisplayed());
        contributionAmount.clear();
        contributionAmount.sendKeys(amount);
    }

    public void selectContributionFrequency(String frequency) {
        assertTrue(contributionFrequency.isDisplayed());
        Select contribution = new Select(contributionFrequency);
        contribution.selectByVisibleText(frequency);
    }

    public void inputRateOfReturn(String rate) {
        assertTrue(rateOfReturn.isDisplayed());
        rateOfReturn.clear();
        rateOfReturn.sendKeys(rate);
    }

    public void clickCalculateBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(calculateBtn)).click();
    }

    public void verifySavingsAmount(String amount) {
        assertTrue(totalSavingsAmount.isDisplayed());
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

    public void verifyRateToolTipContentVisibility() {
        wait.until(ExpectedConditions.visibilityOf(rateTooltipContent));
        assertTrue(rateTooltipContent.isDisplayed());
    }

    public void verifyRateTooltipContent(String expectedContent) {
        assertTrue(rateTooltipContent.getText().contains(expectedContent));
    }

    public void verifyRateErrorMsgVisibility() {
        assertTrue(rateOfReturnErrorMsg.isDisplayed());
    }

    public void verifyRateErrorMsg(String expectedError) {
        assertTrue(expectedError, rateOfReturnErrorMsg.getText().contains(expectedError));
    }
}

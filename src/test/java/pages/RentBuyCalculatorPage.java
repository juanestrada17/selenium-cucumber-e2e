package pages;

import data.DataFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;

public class RentBuyCalculatorPage extends BasePage{


    @FindBy(tagName = "h1")
    private WebElement pageTitle;
    @FindBy(xpath = "//*[@id='rentForm']//input | //*[@id='rentForm']//select")
    private List<WebElement> formFields;
    @FindBy(id = "rent")
    private WebElement rentAmount;
    @FindBy(id = "interestRate")
    private WebElement interestRate;
    @FindBy(id = "amortization")
    private WebElement amortizationPeriod;
    @FindBy(id = "downPaymentPercent")
    private WebElement downPayment;
    @FindBy(id = "rentFormSubmit")
    private WebElement calculateBtn;
    @FindBy(xpath = "//button[@data-id='amort-tooltip']")
    private WebElement amortizationTooltip;
    @FindBy(id = "amort-tooltip-content")
    private WebElement amortizationTooltipMsg;
    @FindBy(xpath = "//*[@id='customCarousel']//div[contains(@class, 'carousel-item')]")
    private List<WebElement> mortgageCards;
    @FindBy(xpath = "//button[@data-id='dp-tooltip']")
    private WebElement paymentTooltip;
    @FindBy(id = "dp-tooltip-content")
    private WebElement paymentTooltipMsg;
    @FindBy(xpath = "//span[contains(@class, 'error-msg')]")
    private WebElement rentValidationMsg;

    public RentBuyCalculatorPage(WebDriver driver) {
        super(driver);
    }


    // Methods
    public void verifyPageTitle() {
        assertTrue(pageTitle.isDisplayed());
    }

    public void verifyFormFields() {
        assertFalse(formFields.isEmpty());
        assertEquals(DataFile.rentBuyCalculatorFormFields, formFields.size());
    }

    public void verifyMortgageCards(String fiveMortgage, String tenMortgage, String twentyMortgage) {
        assertFalse(mortgageCards.isEmpty());

        List<String> expectedValues = List.of(fiveMortgage, tenMortgage, twentyMortgage);
        assertEquals(expectedValues.size(), mortgageCards.size());

        for (int i = 0; i < mortgageCards.size(); i++) {
            WebElement card = mortgageCards.get(i);

            WebElement priceElement = card.findElement(By.xpath(".//span[contains(@class, 'purchasePriceResult')]"));
            String actualPrice = priceElement.getText().trim();

            String expectedPrice = expectedValues.get(i);
            assertEquals(expectedPrice, actualPrice);
        }
    }


    public void inputRentAmount(String rent) {
        rentAmount.sendKeys(rent);
    }

    public void inputInterestRate(String interest) {
        interestRate.clear();
        interestRate.sendKeys(interest);
    }

    public void selectAmortizationPeriod(String period) {
        Select select = new Select(amortizationPeriod);
        select.selectByVisibleText(period);
    }

    public void selectDownPayment(String payment) {
        Select select = new Select(downPayment);
        select.selectByVisibleText(payment);
    }

    public void clickCalculateBtn() {
        calculateBtn.click();
    }

    public void clickAmortizationTooltip() {
        amortizationTooltip.click();
    }

    public void verifyAmortizationTooltipMsg(String message) {
        assertTrue(amortizationTooltipMsg.getText().contains(message));
    }

    public void verifyCardsAmount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(mortgageCards));
        assertEquals(DataFile.rentBuyCalculatorCardAmount, mortgageCards.size());
    }

    public void clickPaymentTooltip() {
        paymentTooltip.click();
    }

    public void verifyPaymentTooltipMsg(String message) {
        assertTrue(paymentTooltipMsg.getText().contains(message));
    }

    public void verifyRentValidationMessage(String message) {
        assertTrue(rentValidationMsg.getText().contains(message));
    }

    public void verifyCalculateButtonDisabled() {
        action.moveToElement(calculateBtn).click().perform();
        assertFalse(calculateBtn.isEnabled());
    }
}

package pages;

import data.DataFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomePage {
    WebDriver driver;
    Actions action;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // locators
    @FindBy(xpath = "//h1[contains(text(), 'Welcome to RBC')]")
    private WebElement welcomeText;
    @FindBy(linkText = "Investments")
    private WebElement investmentsLink;
    @FindBy(linkText = "RRSP Calculator")
    private WebElement rrspCalculatorLink;


    // methods
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void verifyPageUrl() {
        assertEquals(DataFile.homePageUrl, getUrl());
    }

    public void verifyWelcomeText() {
        assertTrue(welcomeText.isDisplayed());
    }

    public void verifyInvestmentsLink() {
        assertTrue(investmentsLink.isDisplayed());
    }

    public void clickInvestmentsLink() {
        investmentsLink.click();
    }

    public void hoverInvestmentsLink() {
        action.moveToElement(investmentsLink).perform();
    }

    public void clickRRSPCalculatorLnk() {
        hoverInvestmentsLink();
        action.moveToElement(rrspCalculatorLink).click().perform();
    }

}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CookiesPopup {
    WebDriver driver;

    public CookiesPopup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "onetrust-banner-sdk")
    private WebElement cookiesBanner;
    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptAllCookies;

    public void clickAcceptAllCookies() {
        acceptAllCookies.click();
    }
}

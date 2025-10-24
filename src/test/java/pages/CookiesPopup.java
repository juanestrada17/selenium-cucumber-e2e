package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;


public class CookiesPopup extends BasePage {

    @FindBy(id = "onetrust-banner-sdk")
    private WebElement cookiesBanner;
    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptAllCookies;

    public CookiesPopup(WebDriver driver) {
        super(driver);
    }

    public void clickAcceptAllCookies() {
        acceptAllCookies.click();
    }
}

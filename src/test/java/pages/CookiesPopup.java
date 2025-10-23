package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v139.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class CookiesPopup {
    WebDriver driver;
    public CookiesPopup(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "onetrust-banner-sdk")
    private WebElement cookiesBanner;
    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptAllCookies;

    public void verifyCookiesBannerVisibility(){
        assertTrue(cookiesBanner.isDisplayed());
    }

    public void clickAcceptAllCookies(){
        acceptAllCookies.click();
    }
}

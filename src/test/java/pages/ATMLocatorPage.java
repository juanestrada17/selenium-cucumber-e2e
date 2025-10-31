package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ATMLocatorPage extends BasePage {


    @FindBy(xpath = "//div[contains(@class, 'map-sidebar-content')]")
    private WebElement mapSideBar;
    @FindBy(xpath = "//div[contains(@class, 'map-sidebar-card') and @data-page='1' and @data-id]")
    private List<WebElement> mapSideBarCards;
    @FindBy(xpath = "//button[contains(@class, 'filters-btn') and @title='Open filter dialog']")
    private WebElement filterBtn;
    @FindBy(id = "share-your-location")
    private WebElement shareLocationBtn;
    @FindBy(id = "page-title")
    private WebElement pageTitle;

    public ATMLocatorPage(WebDriver driver) {
        super(driver);
    }

    public void verifyVisibleSideBar() {
        mapSideBar.isDisplayed();
    }

    public void verifyCardElementsAmount(int resultAmount) {
        assertEquals(resultAmount, mapSideBarCards.size());
    }

    public void verifyFirstCardAddress(String address) {
        assertTrue(mapSideBarCards.getFirst().getText().contains(address));
    }

    public void verifyPageTitle(String message) {
        assertTrue(pageTitle.getText().contains(message));
    }

    public void verifyShareLocationBtn(String message) {
        assertTrue(shareLocationBtn.getText().contains(message));
    }

}

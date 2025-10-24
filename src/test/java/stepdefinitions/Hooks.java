package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.CookiesPopup;
import pages.HomePage;
import pages.RSSPCalculatorPage;
import pages.RentBuyCalculatorPage;
import utilities.DriverUtilities;


public class Hooks {
    static WebDriver driver;
    static DriverUtilities driverUtilities;
    static HomePage homePage;
    static RSSPCalculatorPage rsspCalculatorPage;
    static CookiesPopup cookiesPopup;
    static RentBuyCalculatorPage rentBuyCalculatorPage;

    @Before
    public void init() {
        driverUtilities = DriverUtilities.getInstance();
        driver = driverUtilities.getDriver();
        driver.manage().window().maximize();
        // TODO = add implicit wait

        homePage = new HomePage(driver);
        rsspCalculatorPage = new RSSPCalculatorPage(driver);
        cookiesPopup = new CookiesPopup(driver);
        rentBuyCalculatorPage = new RentBuyCalculatorPage(driver);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        DriverUtilities.resetDriver();
    }
}

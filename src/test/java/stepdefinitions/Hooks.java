package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.CookiesPopup;
import pages.HomePage;
import pages.RSSPCalculatorPage;
import utilities.DriverUtilities;


public class Hooks {
    static WebDriver driver;
    static DriverUtilities driverUtilities;
    static HomePage homePage;
    static RSSPCalculatorPage rsspCalculatorPage;
    static CookiesPopup cookiesPopup;

    @Before
    public void init() {
        driverUtilities = DriverUtilities.getInstance();
        driver = driverUtilities.getDriver();
        driver.manage().window().maximize();
        // TODO = add implicit wait

        homePage = new HomePage(driver);
        rsspCalculatorPage = new RSSPCalculatorPage(driver);
        cookiesPopup = new CookiesPopup(driver);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        DriverUtilities.resetDriver();
    }
}

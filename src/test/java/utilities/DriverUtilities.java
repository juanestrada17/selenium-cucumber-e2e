package utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverUtilities {
    private static DriverUtilities driverUtilities;
    private static WebDriver driver;

    private DriverUtilities() {
        super();
    }

    public static DriverUtilities getInstance() {
        if (driverUtilities == null) {
            driverUtilities = new DriverUtilities();
        }
        return driverUtilities;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    public void createDriver() {
        String browser = getBrowser();
        switch (browser) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
        }
    }

    public String getBrowser() {
        Properties config = new Properties();
        try {
            config.load(new FileInputStream("C:\\Users\\juane\\IdeaProjects\\rbcproject\\src\\test\\java\\utilities\\config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return config.getProperty("Browser");
    }

    public static void resetDriver(){
        driver.quit();
        driverUtilities = null;
        driver = null;
    }
}


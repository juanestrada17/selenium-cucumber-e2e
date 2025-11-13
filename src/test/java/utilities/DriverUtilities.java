package utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in resources folder");
            }
            config.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties", e);
        }

        return config.getProperty("Browser");
    }

    public static void resetDriver() {
        driver.quit();
        driverUtilities = null;
        driver = null;
    }
}


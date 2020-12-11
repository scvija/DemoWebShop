package com.prodyna.configuration;

import com.prodyna.pageObjects.Header;
import com.prodyna.services.LoginService;
import com.prodyna.services.SeleniumService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTestConfiguration {
    public WebDriver driver;
    public static SoftAssert softAssert = new SoftAssert();
    protected SeleniumService seleniumService;
    protected Header header;
    protected LoginService loginService;


    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();

        seleniumService = new SeleniumService(driver);
        header = new Header(driver);
        loginService = new LoginService(driver);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/com/prodyna/resources/data.properties");

        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }

        if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

}




package com.prodyna.configuration;

import com.prodyna.pageObjects.Search;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

public class BaseTest {
    public WebDriver driver;
    public static SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
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



    public void enterText(WebElement element, String inputText){
        element.clear();
        element.sendKeys(inputText);
    }


    public void clickElement(WebElement element) {
        element.click();
    }

    public void navigateToPage(String page) {
        driver.get(page);
    }

    public void verifyElementsAreDisplayed(WebElement... list) {
        for (WebElement element : list) {
            softAssert.assertTrue(element.isDisplayed());
        }

        softAssert.assertAll();
    }

    public void verifyElementsAreDisplayed(List<WebElement> list) {
        for (WebElement element : list) {
            softAssert.assertTrue(isElementDisplayed(element), element + " element not found");
        }

        softAssert.assertAll();
    }


    public boolean compareWithExpected(WebElement element, String expectedText) {
        String elementText = element.getText();
        return elementText.equalsIgnoreCase(expectedText);
    }

    public void selectValueInField(WebElement element, String value) {
        Select selector = new Select(element);
        selector.selectByVisibleText(value);
    }

    public boolean isElementDisplayed(WebElement element) {

        boolean isElementDisplayedValue;
        try {
            isElementDisplayedValue = element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            isElementDisplayedValue = false;
        }
        return isElementDisplayedValue;
    }

    public int countElementsUsingLocator(By locator) {
        return driver.findElements(locator).size();
    }

    public String getFieldText(WebElement element){
        return element.getAttribute("value");

    }

    public void waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean textContainsIgnoreCase(String containing, String toBeContained) {
        return containsIgnoreCase(containing, toBeContained);
    }

    public void startHeaderSearch(String text) {
        Search search = new Search(driver);

        enterText(search.headerSearchButton, text);

        search.headerSearchButton.click();
    }
}




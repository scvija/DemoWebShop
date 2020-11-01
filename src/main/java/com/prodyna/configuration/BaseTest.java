package com.prodyna.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.*;

public class BaseTest {
    public WebDriver driver;
    public SoftAssert softAssert = new SoftAssert();

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

    public String homepageUrl() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/com/prodyna/resources/data.properties");

        prop.load(fis);
        return prop.getProperty("homepage");
    }

    public void enterText(WebElement element, String inputText){
        element.clear();
        element.sendKeys(inputText);
    }

    public boolean isElementDisplayed(WebElement element){
        return element.isDisplayed();
    }

    public void clickElement(WebElement element){
        element.click();
    }

    public void navigateToPage(String page){
        driver.get(page);
    }

    public boolean areElementsDisplayed(List<WebElement> list) {
        boolean isDisplayed = false;
        for (WebElement element : list) {
           isDisplayed = element.isDisplayed();
        }
    return isDisplayed;
    }

    public boolean compareWithExpected(String actualText,String expectedText){
        if (actualText.equalsIgnoreCase(expectedText)) {
            return true;
        }
        return false;
    }

    public void selectValueInField(WebElement element, String value){
        Select selector = new Select(element);
        selector.selectByVisibleText(value);
    }

    public boolean elementNotFound(WebElement element) {

        boolean isNextpageDisplayed = false;
        try {
            isNextpageDisplayed = isElementDisplayed(element);
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            isNextpageDisplayed = true;
        }
        return isNextpageDisplayed;
    }

    public int countElementsUsingLocator(By locator) {

        int count = driver.findElements(locator).size();
        return count;
    }

    public String getFieldText(WebElement element){
        String textValue = element.getAttribute("value");
        return textValue;
    }

    public void waitUntilVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 5 );
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean textContainsIgnoreCase(String containing, String toBeContained){
         return containsIgnoreCase(containing, toBeContained);
        }

}




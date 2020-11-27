package com.prodyna.services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class SeleniumService {
    public WebDriver driver;
    public static SoftAssert softAssert = new SoftAssert();

    public SeleniumService(WebDriver driver) {
        this.driver = driver;
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

}

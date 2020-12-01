package com.prodyna.services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AssertService {
    private WebDriver driver;

    public AssertService(WebDriver driver) {
        this.driver = driver;
    }

    public void assertUrlContainsExpected(String expectedStringInUrl) {
        SeleniumService seleniumService = new SeleniumService(driver);
        String actualUrl = seleniumService.getCurrentUrl();
        assertTrue(actualUrl.contains(expectedStringInUrl), "Actual Url is: " + actualUrl);
    }

    public void assertUrlEqualsToExpected(String expectedUrl) {
        SeleniumService seleniumService = new SeleniumService(driver);
        String actualUrl = seleniumService.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "Actual Url is: " + actualUrl);
    }

    public void assertElementIsDisplayed(WebElement element){
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(element.isDisplayed(), "Element not found: " + element);

    }

    public void assertElementsAreDisplayed(WebElement... elements) {
        SoftAssert softAssert = new SoftAssert();

        for (WebElement element : elements) {
            softAssert.assertTrue(element.isDisplayed(), "Element not found: " + element);
        }

        softAssert.assertAll();
    }

    public void assertElementsAreDisplayed(List<WebElement> list) {
        SoftAssert softAssert = new SoftAssert();
        SeleniumService seleniumService = new SeleniumService(driver);

        for (WebElement element : list) {
            softAssert.assertTrue(seleniumService.isElementDisplayed(element), "Element not found: " + element);
        }
        softAssert.assertAll();
    }

    public void assertEqualsWebElementsText(WebElement element1, WebElement element2){
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(element1.getText().equalsIgnoreCase(element2.getText()),
                "Elements do not have equal text: " + element1.getText() + ", " + element2.getText() );

        softAssert.assertAll();

    }


}

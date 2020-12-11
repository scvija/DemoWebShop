package com.prodyna.services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

import static com.prodyna.pageObjects.Search.SEARCH_PARTIAL_VALID;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AssertService extends  SeleniumService {

    public AssertService(WebDriver driver) {
        super(driver);
    }


    public void assertUrlContainsExpected(String expectedStringInUrl) {
        SeleniumService seleniumService = new SeleniumService(driver);
        String actualUrl = seleniumService.getCurrentUrl();
        assertTrue(actualUrl.contains(expectedStringInUrl), "Actual Url is: " + actualUrl);
    }
    // TODO  soft assert only when there are more asserts in a test
    public void assertUrlEqualsToExpected(String expectedUrl) {
        SeleniumService seleniumService = new SeleniumService(driver);
        String actualUrl = seleniumService.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "Actual Url is: " + actualUrl);
    }

    public void assertElementIsDisplayed(WebElement element){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(element.isDisplayed(), "Element not found: " + element);

        softAssert.assertAll();

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

    public void assertElementNotDisplayed(WebElement element){
        SoftAssert softAssert = new SoftAssert();
        SeleniumService seleniumService = new SeleniumService(driver);

        softAssert.assertFalse(seleniumService.isElementDisplayed(element));
        softAssert.assertAll();
    }

    public void assertElementTextEqualsText(WebElement element, String expectedText) {
        SoftAssert softAssert = new SoftAssert();

        String elementText = element.getText();
        softAssert.assertTrue(elementText.equalsIgnoreCase(expectedText));

        softAssert.assertAll();

    }

    public void assertFieldStandardTextEqualsText(WebElement element, String text) {
        SoftAssert softAssert = new SoftAssert();
        SeleniumService seleniumService = new SeleniumService(driver);

        softAssert.assertEquals(seleniumService.getFieldText(element), text);

        softAssert.assertAll();
    }

    public void assertElementTextContainsIgnoreCase(WebElement element, String text){
        SoftAssert softAssert = new SoftAssert();
        SeleniumService seleniumService = new SeleniumService(driver);

        softAssert.assertTrue(seleniumService.textContainsIgnoreCase(element.getText(), text));

        softAssert.assertAll();
    }

    public void assertTextFromMap(Map<String,String> map){
        SoftAssert softAssert = new SoftAssert();

        for(String actualText : map.keySet()){
            String expectedText = map.get(actualText);
            softAssert.assertEquals(actualText,expectedText, "These values are not equal: "+ actualText + " - " + expectedText);
        }
        softAssert.assertAll();
    }

}

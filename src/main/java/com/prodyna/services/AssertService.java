package com.prodyna.services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class AssertService extends  SeleniumService {

    public AssertService(WebDriver driver) {
        super(driver);
    }


    public void assertUrlContainsExpected(String expectedStringInUrl) {
        String actualUrl = getCurrentUrl();
        assertTrue(actualUrl.contains(expectedStringInUrl), "Actual Url is: " + actualUrl);
    }
    // TODO  soft assert only when there are more asserts in a test
    public void assertUrlEqualsToExpected(String expectedUrl) {
        String actualUrl = getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "Actual Url is: " + actualUrl);
    }

    public void assertElementIsDisplayed(WebElement element){
        assertTrue(element.isDisplayed(), "Element not found: " + element);

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

        for (WebElement element : list) {
            softAssert.assertTrue(isElementDisplayed(element), "Element not found: " + element);
        }
        softAssert.assertAll();
    }


    public void assertEqualsWebElementsText(WebElement element1, WebElement element2){

        assertTrue(element1.getText().equalsIgnoreCase(element2.getText()),
                "Elements do not have equal text: " + element1.getText() + ", " + element2.getText());

    }

    public void assertElementNotDisplayed(WebElement element){
        assertFalse(isElementDisplayed(element));
    }

    public void assertElementTextEqualsText(WebElement element, String expectedText) {

        String elementText = element.getText();
        assertTrue(elementText.equalsIgnoreCase(expectedText));

    }

    public void assertFieldStandardTextEqualsText(WebElement element, String text) {

        assertEquals(getFieldText(element), text);

        softAssert.assertAll();
    }

    public void assertElementTextContainsIgnoreCase(WebElement element, String text){

        softAssert.assertTrue(textContainsIgnoreCase(element.getText(), text));

        softAssert.assertAll();
    }

    public void assertTextFromMap(Map<String, String> map) {
        SoftAssert softAssert = new SoftAssert();

        for (String actualText : map.keySet()) {
            String expectedText = map.get(actualText);
            softAssert.assertEquals(actualText, expectedText, "These values are not equal: " + actualText + " - " + expectedText);
        }
        softAssert.assertAll();
    }

    public void assertCheckboxIsNotSelected(WebElement element) {
        assertFalse(getCheckboxValue(element));
    }

}

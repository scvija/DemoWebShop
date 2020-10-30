package com.prodyna.test;

import com.prodyna.configuration.BaseTest;
import com.prodyna.pageObjects.ProductPage;
import com.prodyna.pageObjects.Search;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static com.prodyna.utility.Constants.*;

public class SearchTest extends BaseTest {

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
        navigateToPage(searchUrl);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void headerSearchTest() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        isElementDisplayed(search.headerSearch);
        isElementDisplayed(search.headerSearchButton);

        softAssert.assertEquals(getFieldText(search.headerSearch), headerSearchStandardText);

        search.headerSearch.click();
        softAssert.assertEquals(getFieldText(search.headerSearch),"");

        enterText(search.headerSearch, searchShort);
        search.headerSearchButton.click();
        // assertrue contains or even equals, the url is predictable
        // softAssert.assertTrue(driver.getCurrentUrl().contains(searchUrl));
        softAssert.assertEquals(products.pageTitle.getText(), searchTitle);

        String advancedSearchFieldValue = getFieldText(search.advancedSearchFieldInput);
        softAssert.assertEquals(advancedSearchFieldValue,searchShort);

        enterText(search.headerSearch, searchValidPartial);


        waitUntilVisible(search.headerSearchAutocompleteDialogue);
        softAssert.assertTrue(search.headerSearchAutocompleteDialogue.isDisplayed());


        System.out.println(search.headerSearchFirstRecommendation.getText());
        softAssert.assertAll();



    }
}

package com.prodyna.test;

import com.prodyna.configuration.BaseTest;
import com.prodyna.pageObjects.ProductPage;
import com.prodyna.pageObjects.Search;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void headerSearchTestLayout() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        softAssert.assertTrue(isElementDisplayed(search.headerSearch));
        softAssert.assertTrue(isElementDisplayed(search.headerSearchButton));

        softAssert.assertEquals(getFieldText(search.headerSearch), headerSearchStandardText);

        search.headerSearch.click();
        softAssert.assertEquals(getFieldText(search.headerSearch), "");

        softAssert.assertAll();
    }

    @Test
    public void headerSearchShort() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);


        enterText(search.headerSearch, searchShort);
        clickElement(search.headerSearchButton);

        softAssert.assertEquals(products.pageTitle.getText(), searchTitle);
        softAssert.assertEquals(search.warning.getText(), minSearchLength);

        String advancedSearchFieldValue = getFieldText(search.advancedSearchFieldInput);
        softAssert.assertEquals(advancedSearchFieldValue, searchShort);

        softAssert.assertAll();
    }

    @Test
    public void headerSearchAutocomplete() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        enterText(search.headerSearch, searchPartialValid);

        waitUntilVisible(search.headerSearchAutocompleteDialogue);
        softAssert.assertTrue(search.headerSearchAutocompleteDialogue.isDisplayed());

        String firstRecommendationText = search.headerSearchFirstRecommendation.getText();
        softAssert.assertTrue(textContainsIgnoreCase(firstRecommendationText, searchPartialValid));

        clickElement(search.headerSearchFirstRecommendation);
        softAssert.assertEquals(products.productName.getText(), firstRecommendationText);

        softAssert.assertAll();

    }

    @Test
    public void headerSearchInvalid() {
        Search search = new Search(driver);

        enterText(search.headerSearch, searchInvalid);
        softAssert.assertFalse(search.headerSearchAutocompleteDialogue.isDisplayed());

        clickElement(search.headerSearchButton);
        softAssert.assertEquals(search.result.getText(), noSearchResults);

        clickElement(search.headerSearchButton);

        driver.switchTo().alert().accept();

        softAssert.assertAll();
    }
    @Test
    public void headerSearchValid() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        enterText(search.headerSearch, searchPartialValid);

        clickElement(search.headerSearchButton);

        softAssert.assertEquals(products.productTileTitle.getText(), searchHealthBook);

        softAssert.assertAll();


    }

    @Test
    public void advancedSearchLayout() {
        Search search = new Search(driver);

        softAssert.assertTrue(isElementDisplayed(search.advancedSearchFieldLabel));
        softAssert.assertTrue(isElementDisplayed(search.advancedSearchFieldInput));

        boolean advancedSearchCheckboxValue = search.advancedSearch.isSelected();

        softAssert.assertFalse(advancedSearchCheckboxValue);

        clickElement(search.advancedSearch);

        softAssert.assertTrue(isElementDisplayed(search.categoryLabel));
        softAssert.assertTrue(isElementDisplayed(search.categoryDropdown));

        softAssert.assertTrue(isElementDisplayed(search.autoSearchSubCategories));

        softAssert.assertTrue(isElementDisplayed(search.manufacturerLabel));
        softAssert.assertTrue(isElementDisplayed(search.manufacturerDropdown));

        softAssert.assertTrue(isElementDisplayed(search.priceRangeLabel));
        softAssert.assertTrue(isElementDisplayed(search.priceFromInput));
        softAssert.assertTrue(isElementDisplayed(search.priceToInput));

        softAssert.assertTrue(isElementDisplayed(search.searchDescriptions));

        softAssert.assertTrue(isElementDisplayed(search.advancedSearchButton));

        softAssert.assertAll();

    }

    @Test
    public void advancedSearchNegativeTest() {
        Search search = new Search(driver);

        search.startAdvancedSearch(searchShort);

        softAssert.assertEquals(search.warning.getText(), minSearchLength);

        search.startAdvancedSearch(emailInvalid);

        softAssert.assertEquals(search.result.getText(), noSearchResults);
        softAssert.assertAll();
    }

////    @Test
//    public void advancedSearchIgnoreCase{
//        Search search = new Search(driver);
//        ProductPage products = new ProductPage(driver);
//
//        search.startAdvancedSearch(computerCapital);
//        int countWithCapital = countElementsUsingLocator(products.product);
//
//        search.startAdvancedSearch(computerLower);
//        int countWithLower = countElementsUsingLocator(products.product);
//
//        softAssert.assertEquals(countWithCapital, countWithLower);
//
//    }
//
}

package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.ProductPage;
import com.prodyna.pageObjects.Search;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.prodyna.utility.Constants.*;

public class SearchTest extends BaseTestConfiguration {

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void headerSearchTestLayout() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(searchUrl);

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

        navigateToPage(searchUrl);

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

        navigateToPage(searchUrl);

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

        navigateToPage(searchUrl);

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

        navigateToPage(searchUrl);

        enterText(search.headerSearch, searchPartialValid);

        clickElement(search.headerSearchButton);

        softAssert.assertEquals(products.productTileTitle.getText(), searchHealthBook);

        softAssert.assertAll();


    }

    @Test
    public void advancedSearchLayout() {
        Search search = new Search(driver);

        navigateToPage(searchUrl);

        softAssert.assertTrue(isElementDisplayed(search.advancedSearchFieldLabel));
        softAssert.assertTrue(isElementDisplayed(search.advancedSearchFieldInput));

        boolean advancedSearchCheckboxValue = search.advancedSearchCheckbox.isSelected();

        softAssert.assertFalse(advancedSearchCheckboxValue);

        clickElement(search.advancedSearchCheckbox);

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

        navigateToPage(searchUrl);

        search.startAdvancedSearch(searchShort);

        softAssert.assertEquals(search.warning.getText(), minSearchLength);

        search.startAdvancedSearch(emailInvalid);

        softAssert.assertEquals(search.result.getText(), noSearchResults);
        softAssert.assertAll();
    }

    @Test
    public void advancedSearchIgnoreCaseTest() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(searchUrl);

        search.startAdvancedSearch(computerCapital);
        int countWithCapital = countElementsUsingLocator(products.product);


        search.startAdvancedSearch(computerLower);
        int countWithLower = countElementsUsingLocator(products.product);

        softAssert.assertEquals(countWithCapital, countWithLower);

        softAssert.assertAll();
    }

    @Test
    public void advancedSearchProductInDifferentCategoryTest() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(searchUrl);

        clickElement(search.advancedSearchCheckbox);

        selectValueInField(search.categoryDropdown, searchCategoryBooks);
        search.startAdvancedSearch(computerLower);

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        search.startAdvancedSearch(searchHealthBook);

        int numberOfProducts = countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProducts > 0);

        softAssert.assertAll();
    }

    @Test
    public void advancedSearchProductInSubCategoryTest() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(searchUrl);

        clickElement(search.advancedSearchCheckbox);

        selectValueInField(search.categoryDropdown, searchCategoryComputers);
        search.startAdvancedSearch(computerLower);

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        clickElement(search.autoSearchSubCategories);

        search.startAdvancedSearch(computerLower);

        int numberOfProducts = countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProducts > 0);

        softAssert.assertAll();
    }

    @Test
    public void advancedSearchManufacturerTest() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(searchUrl);

        clickElement(search.advancedSearchCheckbox);

        selectValueInField(search.manufacturerDropdown, searchManufacturerTricentis);
        search.startAdvancedSearch(computerLower);

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        selectValueInField(search.manufacturerDropdown, searchManufacturerAll);
        search.startAdvancedSearch(computerLower);

        int numberOfProducts = countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProducts > 0);

        softAssert.assertAll();
    }
    @Test
    public void advancedSearchFromPriceTest() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(searchUrl);

        clickElement(search.advancedSearchCheckbox);

        enterText(search.priceFromInput, searchPriceHigh);
        search.startAdvancedSearch(computerLower);

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        enterText(search.priceFromInput, searchPriceMiddle);
        search.startAdvancedSearch(computerLower);

        int numberOfProductsMiddlePrice = countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsMiddlePrice > 0);

        enterText(search.priceFromInput, searchPriceLow);
        search.startAdvancedSearch(computerLower);

        int numberOfProductsLowPrice = countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsLowPrice > numberOfProductsMiddlePrice);

        softAssert.assertAll();
    }
    @Test
    public void advancedSearchToPriceTest() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(searchUrl);

        clickElement(search.advancedSearchCheckbox);

        enterText(search.priceToInput, searchPriceLow);
        search.startAdvancedSearch(computerLower);

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        enterText(search.priceToInput, searchPriceMiddle);
        search.startAdvancedSearch(computerLower);

        int numberOfProductsMiddlePrice = countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsMiddlePrice > 0);

        enterText(search.priceToInput, searchPriceHigh);
        search.startAdvancedSearch(computerLower);

        int numberOfProductsHighPrice = countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsHighPrice > numberOfProductsMiddlePrice);

        softAssert.assertAll();
    }

    @Test
    public void advancedSearchInDescriptionTest() {
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(searchUrl);

        clickElement(search.advancedSearchCheckbox);

        search.startAdvancedSearch(searchBookPartialDescription);

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        clickElement(search.searchDescriptions);
        search.startAdvancedSearch(searchBookPartialDescription);

        int numberOfProductsMiddlePrice = countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsMiddlePrice > 0);

        clickElement(products.productTileTitle);

        softAssert.assertTrue(textContainsIgnoreCase(products.productShortDescription.getText(), searchBookPartialDescription), "The text is not contained in the product description");

        softAssert.assertAll();
    }



}

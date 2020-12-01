package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.ProductPage;
import com.prodyna.pageObjects.Search;
import com.prodyna.services.SearchService;
import com.prodyna.services.SeleniumService;
import org.testng.annotations.Test;

import static com.prodyna.pageObjects.Search.*;
import static com.prodyna.utility.Constants.*;
import static com.prodyna.pageObjects.RegisterPage.*;


public class SearchTest extends BaseTestConfiguration {

    @Test
    public void headerSearchTestLayout() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);

        seleniumService.navigateToPage(searchUrl);

        softAssert.assertTrue(seleniumService.isElementDisplayed(search.headerSearch));
        softAssert.assertTrue(seleniumService.isElementDisplayed(search.headerSearchButton));

        softAssert.assertEquals(seleniumService.getFieldText(search.headerSearch), HEADER_SEARCH_STANDARD_TEXT);

        search.headerSearch.click();
        softAssert.assertEquals(seleniumService.getFieldText(search.headerSearch), "");

        softAssert.assertAll();
    }

    @Test
    public void headerSearchShort() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.enterText(search.headerSearch, SEARCH_SHORT);
        seleniumService.clickElement(search.headerSearchButton);

        softAssert.assertEquals(products.pageTitle.getText(), SEARCH_TITLE);
        softAssert.assertEquals(search.warning.getText(), MIN_SEARCH_LENGTH);

        String advancedSearchFieldValue = seleniumService.getFieldText(search.advancedSearchFieldInput);
        softAssert.assertEquals(advancedSearchFieldValue, SEARCH_SHORT);

        softAssert.assertAll();
    }

    @Test
    public void headerSearchAutocomplete() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.enterText(search.headerSearch, SEARCH_PARTIAL_VALID);

        seleniumService.waitUntilVisible(search.headerSearchAutocompleteDialogue);
        softAssert.assertTrue(search.headerSearchAutocompleteDialogue.isDisplayed());

        String firstRecommendationText = search.headerSearchFirstRecommendation.getText();
        softAssert.assertTrue(seleniumService.textContainsIgnoreCase(firstRecommendationText, SEARCH_PARTIAL_VALID));

        seleniumService.clickElement(search.headerSearchFirstRecommendation);
        softAssert.assertEquals(products.productName.getText(), firstRecommendationText);

        softAssert.assertAll();

    }

    @Test
    public void headerSearchInvalid() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.enterText(search.headerSearch, SEARCH_INVALID);
        softAssert.assertFalse(search.headerSearchAutocompleteDialogue.isDisplayed());

        seleniumService.clickElement(search.headerSearchButton);
        softAssert.assertEquals(search.result.getText(), NO_SEARCH_RESULTS);

        seleniumService.clickElement(search.headerSearchButton);

        seleniumService.acceptAlert();

        softAssert.assertAll();
    }
    @Test
    public void headerSearchValid() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.enterText(search.headerSearch, SEARCH_PARTIAL_VALID);

        seleniumService.clickElement(search.headerSearchButton);

        softAssert.assertEquals(products.productTileTitle.getText(), SEARCH_HEALTH_BOOK);

        softAssert.assertAll();


    }

    @Test
    public void advancedSearchLayout() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);

        seleniumService.navigateToPage(searchUrl);

        boolean advancedSearchCheckboxValue = search.advancedSearchCheckbox.isSelected();
        softAssert.assertFalse(advancedSearchCheckboxValue);

        seleniumService.clickElement(search.advancedSearchCheckbox);

        searchService.verifyAdvancedSearchElementsAreDisplayed();

        softAssert.assertAll();

    }

    @Test
    public void advancedSearchNegativeTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);

        seleniumService.navigateToPage(searchUrl);

        searchService.startAdvancedSearch(SEARCH_SHORT);

        softAssert.assertEquals(search.warning.getText(), MIN_SEARCH_LENGTH);

        searchService.startAdvancedSearch(EMAIL_INVALID);

        softAssert.assertEquals(search.result.getText(), NO_SEARCH_RESULTS);
        softAssert.assertAll();
    }

    @Test
    public void advancedSearchIgnoreCaseTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        searchService.startAdvancedSearch(COMPUTER_CAPITAL);
        int countWithCapital = seleniumService.countElementsUsingLocator(products.product);

        searchService.startAdvancedSearch(COMPUTER_LOWER);
        int countWithLower = seleniumService.countElementsUsingLocator(products.product);

        softAssert.assertEquals(countWithCapital, countWithLower);

        softAssert.assertAll();
    }

    @Test
    public void advancedSearchProductInDifferentCategoryTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.clickElement(search.advancedSearchCheckbox);

        seleniumService.selectValueInField(search.categoryDropdown, SEARCH_CATEGORY_BOOKS);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        softAssert.assertEquals(search.result.getText(), NO_SEARCH_RESULTS);

        searchService.startAdvancedSearch(SEARCH_HEALTH_BOOK);

        int numberOfProducts = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProducts > 0);

        softAssert.assertAll();
    }

    @Test
    public void advancedSearchProductInSubCategoryTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.clickElement(search.advancedSearchCheckbox);

        seleniumService.selectValueInField(search.categoryDropdown, SEARCH_CATEGORY_COMPUTERS);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        softAssert.assertEquals(search.result.getText(), NO_SEARCH_RESULTS);

        seleniumService.clickElement(search.autoSearchSubCategories);

        searchService.startAdvancedSearch(COMPUTER_LOWER);

        int numberOfProducts = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProducts > 0);

        softAssert.assertAll();
    }

    @Test
    public void advancedSearchManufacturerTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.clickElement(search.advancedSearchCheckbox);

        seleniumService.selectValueInField(search.manufacturerDropdown, SEARCH_MANUFACTURER_TRICENTIS);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        softAssert.assertEquals(search.result.getText(), NO_SEARCH_RESULTS);

        seleniumService.selectValueInField(search.manufacturerDropdown, SEARCH_MANUFACTURER_ALL);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        int numberOfProducts = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProducts > 0);

        softAssert.assertAll();
    }
    @Test
    public void advancedSearchFromPriceTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        SearchService searchService = new SearchService(driver);
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.clickElement(search.advancedSearchCheckbox);

        seleniumService.enterText(search.priceFromInput, SEARCH_PRICE_HIGH);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        softAssert.assertEquals(search.result.getText(), NO_SEARCH_RESULTS);

        seleniumService.enterText(search.priceFromInput, SEARCH_PRICE_MIDDLE);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        int numberOfProductsMiddlePrice = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsMiddlePrice > 0);

        seleniumService.enterText(search.priceFromInput, SEARCH_PRICE_LOW);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        int numberOfProductsLowPrice = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsLowPrice > numberOfProductsMiddlePrice);

        softAssert.assertAll();
    }
    @Test
    public void advancedSearchToPriceTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        SearchService searchService = new SearchService(driver);
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.clickElement(search.advancedSearchCheckbox);

        seleniumService.enterText(search.priceToInput, SEARCH_PRICE_LOW);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        softAssert.assertEquals(search.result.getText(), NO_SEARCH_RESULTS);

        seleniumService.enterText(search.priceToInput, SEARCH_PRICE_MIDDLE);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        int numberOfProductsMiddlePrice = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsMiddlePrice > 0);

        seleniumService.enterText(search.priceToInput, SEARCH_PRICE_HIGH);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        int numberOfProductsHighPrice = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsHighPrice > numberOfProductsMiddlePrice);

        softAssert.assertAll();
    }

    @Test
    public void advancedSearchInDescriptionTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        SearchService searchService = new SearchService(driver);
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.clickElement(search.advancedSearchCheckbox);

        searchService.startAdvancedSearch(searchBookPartialDescription);

        softAssert.assertEquals(search.result.getText(), NO_SEARCH_RESULTS);

        seleniumService.clickElement(search.searchDescriptions);
        searchService.startAdvancedSearch(searchBookPartialDescription);

        int numberOfProductsMiddlePrice = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsMiddlePrice > 0);

        seleniumService.clickElement(products.productTileTitle);

        softAssert.assertTrue(seleniumService.textContainsIgnoreCase(products.productShortDescription.getText(), searchBookPartialDescription), "The text is not contained in the product description");

        softAssert.assertAll();
    }



}

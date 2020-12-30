package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.ProductPage;
import com.prodyna.pageObjects.Search;
import com.prodyna.services.AssertService;
import com.prodyna.services.SearchService;
import com.prodyna.services.SeleniumService;
import com.prodyna.services.WaitService;
import org.testng.annotations.Test;

import static com.prodyna.pageObjects.RegisterPage.EMAIL_INVALID;
import static com.prodyna.pageObjects.Search.*;
import static com.prodyna.utility.Constants.searchUrl;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class SearchTest extends BaseTestConfiguration {

    @Test
    public void headerSearchTestLayout() {
        SeleniumService seleniumService = new SeleniumService(driver);
        AssertService assertService = new AssertService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);

        seleniumService.navigateToPage(searchUrl);

        searchService.verifyHeaderSearchElements();

        seleniumService.clickElement(search.headerSearch);
        assertService.assertFieldStandardTextEqualsText(search.headerSearch, "");

    }

    @Test
    public void headerSearchShort() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(searchUrl);

        searchService.startHeaderSearch(SEARCH_SHORT);

        assertService.assertElementTextEqualsText(search.pageTitle, SEARCH_TITLE);
        assertService.assertElementTextEqualsText(search.warning, MIN_SEARCH_LENGTH);

        assertService.assertFieldStandardTextEqualsText(search.advancedSearchFieldInput, SEARCH_SHORT);
    }

    @Test
    public void headerSearchAutocomplete() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);
        WaitService waitService = new WaitService(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.enterText(search.headerSearch, SEARCH_PARTIAL_VALID);


        waitService.waitUntilVisible(search.headerSearchAutocompleteDialogue);
        assertService.assertElementIsDisplayed(search.headerSearchAutocompleteDialogue);

        assertService.assertElementTextContainsIgnoreCase(search.headerSearchFirstRecommendation, SEARCH_PARTIAL_VALID);
        String firstRecommendationText = search.headerSearchFirstRecommendation.getText();

        seleniumService.clickElement(search.headerSearchFirstRecommendation);
        assertService.assertElementTextEqualsText(products.productName, firstRecommendationText);

    }

    @Test
    public void headerSearchInvalid() {
        SeleniumService seleniumService = new SeleniumService(driver);
        AssertService assertService = new AssertService(driver);
        Search search = new Search(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.enterText(search.headerSearch, SEARCH_INVALID);
        assertService.assertElementNotDisplayed(search.headerSearchAutocompleteDialogue);

        seleniumService.clickElement(search.headerSearchButton);
        assertService.assertElementTextEqualsText(search.result, NO_SEARCH_RESULTS);

        seleniumService.clickElement(search.headerSearchButton);

        seleniumService.acceptAlert();

    }
    @Test
    public void headerSearchValid() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.enterText(search.headerSearch, SEARCH_PARTIAL_VALID);

        seleniumService.clickElement(search.headerSearchButton);

        assertService.assertElementTextEqualsText(products.productTileTitle, SEARCH_HEALTH_BOOK);

    }

    @Test
    public void advancedSearchLayout() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(searchUrl);

        assertService.assertCheckboxIsNotSelected(search.advancedSearchCheckbox);

        seleniumService.clickElement(search.advancedSearchCheckbox);

        searchService.verifyAdvancedSearchElementsAreDisplayed();

    }

    @Test
    public void advancedSearchNegativeTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(searchUrl);

        searchService.startAdvancedSearch(SEARCH_SHORT);
        assertService.assertElementTextEqualsText(search.warning, MIN_SEARCH_LENGTH);

        searchService.startAdvancedSearch(EMAIL_INVALID);
        assertService.assertElementTextEqualsText(search.result, NO_SEARCH_RESULTS);
    }

    @Test
    public void advancedSearchIgnoreCaseTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        SearchService searchService = new SearchService(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        searchService.startAdvancedSearch(COMPUTER_CAPITAL);
        int countWithCapital = seleniumService.countElementsUsingLocator(products.product);

        searchService.startAdvancedSearch(COMPUTER_LOWER);
        int countWithLower = seleniumService.countElementsUsingLocator(products.product);

        assertEquals(countWithCapital, countWithLower);
    }

    @Test
    public void advancedSearchProductInDifferentCategoryTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);
        ProductPage products = new ProductPage(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.clickElement(search.advancedSearchCheckbox);

        seleniumService.selectValueInField(search.categoryDropdown, SEARCH_CATEGORY_BOOKS);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        assertService.assertElementTextEqualsText(search.result, NO_SEARCH_RESULTS);
        searchService.startAdvancedSearch(SEARCH_HEALTH_BOOK);

        int numberOfProducts = seleniumService.countElementsUsingLocator(products.product);
        assertTrue(numberOfProducts > 0);
    }

    @Test
    public void advancedSearchProductInSubCategoryTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);
        ProductPage products = new ProductPage(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.selectCheckbox(search.advancedSearchCheckbox);

        seleniumService.selectValueInField(search.categoryDropdown, SEARCH_CATEGORY_COMPUTERS);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        assertService.assertElementTextEqualsText(search.result, NO_SEARCH_RESULTS);
        seleniumService.selectCheckbox(search.autoSearchSubCategories);

        searchService.startAdvancedSearch(COMPUTER_LOWER);

        int numberOfProducts = seleniumService.countElementsUsingLocator(products.product);

        assertTrue(numberOfProducts > 0);

    }

    @Test
    public void advancedSearchManufacturerTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);
        ProductPage products = new ProductPage(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.selectCheckbox(search.advancedSearchCheckbox);

        seleniumService.selectValueInField(search.manufacturerDropdown, SEARCH_MANUFACTURER_TRICENTIS);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        assertService.assertElementTextEqualsText(search.result,NO_SEARCH_RESULTS);

        seleniumService.selectValueInField(search.manufacturerDropdown, SEARCH_MANUFACTURER_ALL);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        int numberOfProducts = seleniumService.countElementsUsingLocator(products.product);
        assertTrue(numberOfProducts > 0);

    }
    @Test
    public void advancedSearchFromPriceTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        SearchService searchService = new SearchService(driver);
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.selectCheckbox(search.advancedSearchCheckbox);

        seleniumService.enterText(search.priceFromInput, SEARCH_PRICE_HIGH);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        assertService.assertElementTextEqualsText(search.result,NO_SEARCH_RESULTS);

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
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.selectCheckbox(search.advancedSearchCheckbox);

        seleniumService.enterText(search.priceToInput, SEARCH_PRICE_LOW);
        searchService.startAdvancedSearch(COMPUTER_LOWER);

        assertService.assertElementTextEqualsText(search.result,NO_SEARCH_RESULTS);

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
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.selectCheckbox(search.advancedSearchCheckbox);

        searchService.startAdvancedSearch(SEARCH_BOOK_PARTIAL_DESCRIPTION);

        assertService.assertElementTextEqualsText(search.result,NO_SEARCH_RESULTS);

        seleniumService.clickElement(search.searchDescriptions);
        searchService.startAdvancedSearch(SEARCH_BOOK_PARTIAL_DESCRIPTION);

        int numberOfProductsMiddlePrice = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsMiddlePrice > 0);

        seleniumService.clickElement(products.productTileTitle);

        assertService.assertElementTextContainsIgnoreCase(products.productShortDescription, SEARCH_BOOK_PARTIAL_DESCRIPTION);

        softAssert.assertAll();
    }



}

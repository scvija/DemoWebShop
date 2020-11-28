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

        softAssert.assertEquals(seleniumService.getFieldText(search.headerSearch), headerSearchStandardText);

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

        seleniumService.enterText(search.headerSearch, searchShort);
        seleniumService.clickElement(search.headerSearchButton);

        softAssert.assertEquals(products.pageTitle.getText(), searchTitle);
        softAssert.assertEquals(search.warning.getText(), minSearchLength);

        String advancedSearchFieldValue = seleniumService.getFieldText(search.advancedSearchFieldInput);
        softAssert.assertEquals(advancedSearchFieldValue, searchShort);

        softAssert.assertAll();
    }

    @Test
    public void headerSearchAutocomplete() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.enterText(search.headerSearch, searchPartialValid);

        seleniumService.waitUntilVisible(search.headerSearchAutocompleteDialogue);
        softAssert.assertTrue(search.headerSearchAutocompleteDialogue.isDisplayed());

        String firstRecommendationText = search.headerSearchFirstRecommendation.getText();
        softAssert.assertTrue(seleniumService.textContainsIgnoreCase(firstRecommendationText, searchPartialValid));

        seleniumService.clickElement(search.headerSearchFirstRecommendation);
        softAssert.assertEquals(products.productName.getText(), firstRecommendationText);

        softAssert.assertAll();

    }

    @Test
    public void headerSearchInvalid() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);

        seleniumService.navigateToPage(searchUrl);

        seleniumService.enterText(search.headerSearch, searchInvalid);
        softAssert.assertFalse(search.headerSearchAutocompleteDialogue.isDisplayed());

        seleniumService.clickElement(search.headerSearchButton);
        softAssert.assertEquals(search.result.getText(), noSearchResults);

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

        seleniumService.enterText(search.headerSearch, searchPartialValid);

        seleniumService.clickElement(search.headerSearchButton);

        softAssert.assertEquals(products.productTileTitle.getText(), searchHealthBook);

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

        searchService.startAdvancedSearch(searchShort);

        softAssert.assertEquals(search.warning.getText(), minSearchLength);

        searchService.startAdvancedSearch(emailInvalid);

        softAssert.assertEquals(search.result.getText(), noSearchResults);
        softAssert.assertAll();
    }

    @Test
    public void advancedSearchIgnoreCaseTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Search search = new Search(driver);
        SearchService searchService = new SearchService(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(searchUrl);

        searchService.startAdvancedSearch(computerCapital);
        int countWithCapital = seleniumService.countElementsUsingLocator(products.product);

        searchService.startAdvancedSearch(computerLower);
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

        seleniumService.selectValueInField(search.categoryDropdown, searchCategoryBooks);
        searchService.startAdvancedSearch(computerLower);

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        searchService.startAdvancedSearch(searchHealthBook);

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

        seleniumService.selectValueInField(search.categoryDropdown, searchCategoryComputers);
        searchService.startAdvancedSearch(computerLower);

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        seleniumService.clickElement(search.autoSearchSubCategories);

        searchService.startAdvancedSearch(computerLower);

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

        seleniumService.selectValueInField(search.manufacturerDropdown, searchManufacturerTricentis);
        searchService.startAdvancedSearch(computerLower);

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        seleniumService.selectValueInField(search.manufacturerDropdown, searchManufacturerAll);
        searchService.startAdvancedSearch(computerLower);

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

        seleniumService.enterText(search.priceFromInput, searchPriceHigh);
        searchService.startAdvancedSearch(computerLower);

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        seleniumService.enterText(search.priceFromInput, searchPriceMiddle);
        searchService.startAdvancedSearch(computerLower);

        int numberOfProductsMiddlePrice = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsMiddlePrice > 0);

        seleniumService.enterText(search.priceFromInput, searchPriceLow);
        searchService.startAdvancedSearch(computerLower);

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

        seleniumService.enterText(search.priceToInput, searchPriceLow);
        searchService.startAdvancedSearch(computerLower);

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        seleniumService.enterText(search.priceToInput, searchPriceMiddle);
        searchService.startAdvancedSearch(computerLower);

        int numberOfProductsMiddlePrice = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsMiddlePrice > 0);

        seleniumService.enterText(search.priceToInput, searchPriceHigh);
        searchService.startAdvancedSearch(computerLower);

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

        softAssert.assertEquals(search.result.getText(), noSearchResults);

        seleniumService.clickElement(search.searchDescriptions);
        searchService.startAdvancedSearch(searchBookPartialDescription);

        int numberOfProductsMiddlePrice = seleniumService.countElementsUsingLocator(products.product);
        softAssert.assertTrue(numberOfProductsMiddlePrice > 0);

        seleniumService.clickElement(products.productTileTitle);

        softAssert.assertTrue(seleniumService.textContainsIgnoreCase(products.productShortDescription.getText(), searchBookPartialDescription), "The text is not contained in the product description");

        softAssert.assertAll();
    }



}

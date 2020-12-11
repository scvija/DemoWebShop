package com.prodyna.services;

import com.prodyna.pageObjects.Search;
import com.prodyna.services.SeleniumService;
import org.openqa.selenium.WebDriver;

import static com.prodyna.pageObjects.Search.HEADER_SEARCH_STANDARD_TEXT;
import static com.prodyna.utility.Constants.searchUrl;

public class SearchService extends SeleniumService {

    public SearchService(WebDriver driver) {
        super(driver);
    }

    public void verifyHeaderSearchElements(){
        AssertService assertService = new AssertService(driver);
        Search search = new Search(driver);

        assertService.assertElementsAreDisplayed(search.headerSearch, search.headerSearchButton);
        assertService.assertFieldStandardTextEqualsText(search.headerSearch, HEADER_SEARCH_STANDARD_TEXT);
    }

    public void startAdvancedSearch(String textInput){
        Search search = new Search(driver);

        search.advancedSearchFieldInput.clear();
        search.advancedSearchFieldInput.sendKeys(textInput);

        search.advancedSearchButton.click();
    }

    public void startHeaderSearch(String text) {
        Search search = new Search(driver);

        enterText(search.headerSearch, text);
        clickElement(search.headerSearchButton);
    }

    public void verifyAdvancedSearchElementsAreDisplayed() {
        Search search = new Search(driver);

        softAssert.assertTrue(isElementDisplayed(search.advancedSearchFieldLabel));
        softAssert.assertTrue(isElementDisplayed(search.advancedSearchFieldInput));

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
}

package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.CategoriesMenu;
import com.prodyna.pageObjects.ProductPage;
import com.prodyna.services.AssertService;
import com.prodyna.services.CategoriesService;
import com.prodyna.services.SeleniumService;
import org.testng.annotations.Test;

import static com.prodyna.pageObjects.ProductPage.*;
import static com.prodyna.utility.Constants.*;

public class CategoriesTest extends BaseTestConfiguration {


    @Test
    public void categoriesMenuLayoutTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        CategoriesMenu categories = new CategoriesMenu(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(homepageUrl);
        // not sure if good
        assertService.assertElementsAreDisplayed(categories.getCategoriesElements());
    }

    @Test
    public void categoryNavigationTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        CategoriesMenu categories = new CategoriesMenu(driver);
        CategoriesService categoriesService = new CategoriesService(driver);

        seleniumService.navigateToPage(homepageUrl);

        categoriesService.assertCategoryDetailHasCorrectTitle(categories.books);
        categoriesService.assertCategoryDetailHasCorrectTitle(categories.computers);

    }

    @Test
    public void filteringPaginationTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        CategoriesMenu categories = new CategoriesMenu(driver);
        ProductPage products = new ProductPage(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(homepageUrl);

        seleniumService.clickElement(categories.apparelShoes);
        assertService.assertElementsAreDisplayed(products.getProductSelectors());
        softAssert.assertTrue(seleniumService.isElementDisplayed(products.nextPage));

        //could do it with a look, where to store it? point 12.
        // problem - categories where there is not enough products for a page, like 2,5,9,
        seleniumService.selectValueInField(products.pageSize, DISPLAY4);
        softAssert.assertEquals(seleniumService.countElementsUsingLocator(products.product), 4);

        seleniumService.selectValueInField(products.pageSize, DISPLAY8);
        softAssert.assertEquals(seleniumService.countElementsUsingLocator(products.product), 8);

        seleniumService.selectValueInField(products.pageSize, DISPLAY12);
        softAssert.assertEquals(seleniumService.countElementsUsingLocator(products.product), 12);

        softAssert.assertFalse(seleniumService.isElementDisplayed(products.nextPage));


        softAssert.assertAll();


    }



}

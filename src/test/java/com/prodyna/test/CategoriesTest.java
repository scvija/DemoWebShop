package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.CategoriesMenu;
import com.prodyna.pageObjects.ProductPage;
import com.prodyna.services.SeleniumService;
import org.testng.annotations.Test;

import static com.prodyna.utility.Constants.*;

public class CategoriesTest extends BaseTestConfiguration {


    @Test
    public void categoriesMenuLayoutTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        CategoriesMenu categories = new CategoriesMenu(driver);

        seleniumService.navigateToPage(homepageUrl);
        // not sure if good
        seleniumService.verifyElementsAreDisplayed(categories.getCategoriesElements());
    }

    @Test
    public void categoryNavigationTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        CategoriesMenu categories = new CategoriesMenu(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(homepageUrl);

        seleniumService.clickElement(categories.books);
        softAssert.assertTrue(seleniumService.compareElementTextWithExpected(categories.books, products.pageTitle.getText()));

        // TODO navigateBack method?
        driver.navigate().back();

        seleniumService.clickElement(categories.computers);
        softAssert.assertTrue(seleniumService.compareElementTextWithExpected(categories.computers, products.pageTitle.getText()));
        driver.navigate().back();

        softAssert.assertAll();
    }

    @Test
    public void filteringPaginationTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        CategoriesMenu categories = new CategoriesMenu(driver);
        ProductPage products = new ProductPage(driver);

        seleniumService.navigateToPage(homepageUrl);

        seleniumService.clickElement(categories.apparelShoes);
        seleniumService.verifyElementsAreDisplayed(products.getProductSelectors());
        softAssert.assertTrue(seleniumService.isElementDisplayed(products.nextPage));

        //could do it with a look, where to store it? point 12.
        // problem - categories where there is not enough products for a page, like 2,5,9,
        seleniumService.selectValueInField(products.pageSize, display4);
        softAssert.assertEquals(seleniumService.countElementsUsingLocator(products.product), 4);

        seleniumService.selectValueInField(products.pageSize, display8);
        softAssert.assertEquals(seleniumService.countElementsUsingLocator(products.product), 8);

        seleniumService.selectValueInField(products.pageSize, display12);
        softAssert.assertEquals(seleniumService.countElementsUsingLocator(products.product), 12);

        softAssert.assertFalse(seleniumService.isElementDisplayed(products.nextPage));


        softAssert.assertAll();


    }



}

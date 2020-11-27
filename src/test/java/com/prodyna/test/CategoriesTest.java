package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.CategoriesMenu;
import com.prodyna.pageObjects.ProductPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.prodyna.utility.Constants.*;

public class CategoriesTest extends BaseTestConfiguration {

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }


    @Test
    public void categoriesMenuLayoutTest() {
        CategoriesMenu categories = new CategoriesMenu(driver);

        navigateToPage(homepageUrl);
        // not sure if good
        verifyElementsAreDisplayed(categories.getCategoriesElements());
    }

    @Test
    public void categoryNavigationTest() {
        CategoriesMenu categories = new CategoriesMenu(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(homepageUrl);

        clickElement(categories.books);
        softAssert.assertTrue(compareWithExpected(categories.books, products.pageTitle.getText()));
        driver.navigate().back();

        clickElement(categories.computers);
        softAssert.assertTrue(compareWithExpected(categories.computers, products.pageTitle.getText()));
        driver.navigate().back();

        softAssert.assertAll();
    }

    @Test
    public void filteringPaginationTest() {
        CategoriesMenu categories = new CategoriesMenu(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(homepageUrl);

        clickElement(categories.apparelShoes);
        verifyElementsAreDisplayed(products.getProductSelectors());
        softAssert.assertTrue(isElementDisplayed(products.nextPage));

        //could do it with a look, where to store it? point 12.
        // problem - categories where there is not enough products for a page, like 2,5,9,
        selectValueInField(products.pageSize, display4);
        softAssert.assertEquals(countElementsUsingLocator(products.product), 4);

        selectValueInField(products.pageSize, display8);
        softAssert.assertEquals(countElementsUsingLocator(products.product), 8);

        selectValueInField(products.pageSize, display12);
        softAssert.assertEquals(countElementsUsingLocator(products.product), 12);

        softAssert.assertFalse(isElementDisplayed(products.nextPage));


        softAssert.assertAll();


    }



}

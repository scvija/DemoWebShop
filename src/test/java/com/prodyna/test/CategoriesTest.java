package com.prodyna.test;

import com.prodyna.configuration.BaseTest;
import com.prodyna.pageObjects.CategoriesMenu;
import com.prodyna.pageObjects.ProductPage;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class CategoriesTest extends BaseTest {

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
        navigateToPage(homepageUrl());
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }


    @Test
    public void categoriesMenuLayoutTest() {
        CategoriesMenu categories = new CategoriesMenu(driver);
        // not sure if good
        Assert.assertTrue(areElementsDisplayed(categories.getCategoriesMenu()));
    }

    @Test
    public void categoryNavigationTest() {

        CategoriesMenu categories = new CategoriesMenu(driver);
        ProductPage products = new ProductPage(driver);

        clickElement(categories.books);
        softAssert.assertTrue(compareWithExpected(categories.books.getText(), products.pageTitle.getText()));
        driver.navigate().back();

        clickElement(categories.computers);
        softAssert.assertTrue(compareWithExpected(categories.computers.getText(), products.pageTitle.getText()));
        driver.navigate().back();
    }

    @Test
    public void filteringPaginationTest() {
        CategoriesMenu categories = new CategoriesMenu(driver);
        ProductPage products = new ProductPage(driver);


        categories.getApparelShoes().click();
        softAssert.assertTrue(areElementsDisplayed(products.productSelectors));
        softAssert.assertTrue(isElementDisplayed(products.nextPage));

        selectValueInField(products.pageSize, "12");

        softAssert.assertTrue(elementNotFound(products.nextPage));

        softAssert.assertAll();


    }



}

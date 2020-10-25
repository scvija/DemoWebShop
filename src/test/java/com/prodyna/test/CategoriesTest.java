package com.prodyna.test;

import com.prodyna.configuration.BaseTest;
import com.prodyna.pageObjects.CategoriesMenu;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.prodyna.utility.Constants.*;

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
    public void categoriesMenuLayoutTest(){
        CategoriesMenu categories = new CategoriesMenu(driver);
        // not sure if good
        Assert.assertTrue(areElementsDisplayed(categories.getCategoriesMenu()));
    }


}

package com.prodyna.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.CategoriesMenu;
import com.prodyna.pageObjects.ProductPage;
import com.prodyna.services.AssertService;
import com.prodyna.services.CategoriesService;
import com.prodyna.services.ProductsService;
import com.prodyna.services.SeleniumService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.prodyna.pageObjects.ProductPage.*;
import static com.prodyna.utility.Constants.*;

public class CategoriesTest extends BaseTestConfiguration {

    @BeforeTest
    public void config(){
        String path = System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("First report test");
        extentSparkReporter.config().setDocumentTitle("Test results");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester", "Stefan Cvijanovic");
    }

    @Test
    public void categoriesMenuLayoutTest() {
        extentReports.createTest("categoriesManyLayoutTest");
        SeleniumService seleniumService = new SeleniumService(driver);
        CategoriesMenu categories = new CategoriesMenu(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(homepageUrl);
        // not sure if good
        assertService.assertElementsAreDisplayed(categories.getCategoriesElements());
        extentReports.flush();
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
        ProductsService productService = new ProductsService(driver);

        seleniumService.navigateToPage(homepageUrl);

        seleniumService.clickElement(categories.apparelShoes);
        assertService.assertElementsAreDisplayed(products.getProductSelectors());
        assertService.assertElementIsDisplayed(products.nextPage);

        // should maybe be refactored to not check for the exact number, but that the number is not larger than the selected one
        productService.verifyNumberOfProductPerPage(products.getDisplayProductNumbers() );
        assertService.assertElementNotDisplayed(products.nextPage);


        softAssert.assertAll();


    }



}

package com.prodyna.services;

import com.prodyna.pageObjects.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Stream;

import static com.prodyna.pageObjects.ProductPage.DISPLAY4;

public class ProductsService extends SeleniumService {

    public ProductsService(WebDriver driver) {
        super(driver);
    }

    public void verifyNumberOfProductPerPage(List<String> list) {
        ProductPage products = new ProductPage(driver);

        for (String value : list) {
            selectValueInField(products.pageSize, value);
            int intValue = Integer.parseInt(value);
            softAssert.assertTrue(countElementsUsingLocator(products.product) == intValue, "The number of products on this page is not " + intValue);
        }
        softAssert.assertAll();
    }
}

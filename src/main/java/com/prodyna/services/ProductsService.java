package com.prodyna.services;

import com.prodyna.pageObjects.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsService extends SeleniumService {

    public ProductsService(WebDriver driver) {
        super(driver);
    }

    public boolean verifyCategoriesTitles(List<WebElement> list) {
        ProductPage products = new ProductPage(driver);
        boolean titlesCorrect = true;

        for (WebElement element : list) {
            clickElement(element);
            String elementText = element.getText();

            if (!elementText.equalsIgnoreCase(products.pageTitle.getText())) {
                titlesCorrect = false;
                break;
            }
        }

        return true;
    }
}

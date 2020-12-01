package com.prodyna.services;

import com.prodyna.pageObjects.CategoriesMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoriesService extends SeleniumService {

    public CategoriesService(WebDriver driver) {
        super(driver);
    }
    CategoriesMenu categories = new CategoriesMenu(driver);

    public void openCategory(WebElement element){
        clickElement(element);

    }

    public void assertCategoryDetailHasCorrectTitle(WebElement element){
        CategoriesMenu categoriesMenu = new CategoriesMenu(driver);
        AssertService assertService = new AssertService(driver);

        clickElement(element);
        assertService.assertEqualsWebElementsText(element, categoriesMenu.pageTitle);

    }

}

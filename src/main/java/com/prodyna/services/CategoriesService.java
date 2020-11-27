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

}

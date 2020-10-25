package com.prodyna.pageObjects;

import com.prodyna.configuration.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends BaseTest {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "page-title")
    public WebElement pageTitle;

    public boolean verifyCategoriesTitles(List<WebElement> list) {
        boolean titlesCorrect = true;
        for (WebElement element : list) {
            clickElement(element);
            String elementText = element.getText();

            if (!elementText.equalsIgnoreCase(pageTitle.getText())) {
              titlesCorrect = false;
              break;
            }
        }

        return true;
    }

}

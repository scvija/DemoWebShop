package com.prodyna.pageObjects;

import com.prodyna.configuration.BaseTestConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static com.prodyna.utility.Constants.*;

public class ProductPage extends BaseTestConfiguration {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = titleLocator)
    public WebElement pageTitle;

    @FindBy(id = "products-orderby")
    public WebElement sortBy;

    @FindBy(id = "products-pagesize")
    public WebElement pageSize;

    @FindBy(xpath = "//li[@class='next-page']")
    public WebElement nextPage;

    @FindBy(id = "products-viewmode")
    public WebElement viewMode;

    @FindBy(xpath = "//div[@class='product-selectors']/div")
    public  List<WebElement> productSelectors;

    @FindBy(className = "product-name")
    public WebElement productName;

    @FindBy(className = "product-title")
    public WebElement productTileTitle;

    @FindBy(className = "short-description")
    public WebElement productShortDescription;

    public By product = By.className("product-item");

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

    public List<WebElement> getProductSelectors(){
        List<WebElement> productSelectors = new ArrayList<>();

        productSelectors.add(viewMode);
        productSelectors.add(pageSize);
        productSelectors.add(sortBy);

        return productSelectors;
    }

}

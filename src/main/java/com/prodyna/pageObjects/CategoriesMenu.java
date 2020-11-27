package com.prodyna.pageObjects;

import com.prodyna.configuration.BasePageConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class CategoriesMenu extends BasePageConfiguration {

    public WebDriver driver;

    public CategoriesMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/books']")
    public WebElement books;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/computers']")
    public WebElement computers;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/electronics']")
    public WebElement electronics;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/apparel-shoes']")
    public WebElement apparelShoes;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/digital-downloads']")
    public WebElement digitalDownloads;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/jewelry']")
    public WebElement jewelry;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/gift-cards']")
    public WebElement giftCards;

    @FindBy(xpath = "//ul[@class='top-menu']/li")
    public List<WebElement> categoriesMenu;


    public List<WebElement> getCategoriesElements(){
        List<WebElement> categoriesElements = new ArrayList<>();

        categoriesElements.add(books);
        categoriesElements.add(computers);
        categoriesElements.add(electronics);
        categoriesElements.add(apparelShoes);
        categoriesElements.add(digitalDownloads);
        categoriesElements.add(jewelry);
        categoriesElements.add(giftCards);

        return categoriesElements;
    }
    

    
}

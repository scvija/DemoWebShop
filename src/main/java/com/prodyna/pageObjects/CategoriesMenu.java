package com.prodyna.pageObjects;

import com.prodyna.configuration.BaseTest;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Data
public class CategoriesMenu extends BaseTest {

    public WebDriver driver;
    public SoftAssert softAssert = new SoftAssert();


    public CategoriesMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/books']")
    WebElement books;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/computers']")
    WebElement computers;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/electronics']")
    WebElement electronics;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/apparel-shoes']")
    WebElement apparelShoes;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/digital-downloads']")
    WebElement digitalDownloads;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/jewelry']")
    WebElement jewelry;

    @FindBy(xpath = "//ul[@class='top-menu'] //a[@href='/gift-cards']")
    WebElement giftCards;

    @FindBy(xpath = "//ul[@class='top-menu']/li")
    List<WebElement> categoriesMenu;
    
    

    
}

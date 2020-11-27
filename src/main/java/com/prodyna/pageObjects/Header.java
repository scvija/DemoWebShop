package com.prodyna.pageObjects;

import com.prodyna.configuration.BasePageConfiguration;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;


public class Header extends BasePageConfiguration {

    public WebDriver driver;
    public SoftAssert softAssert;

    public Header(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[@href='/register']")
    public WebElement register;

    @FindBy(xpath = "//a[@href='/login']")
    public WebElement login;

    @FindBy(xpath = "//a[@href='/cart']")
    public WebElement cart;

    @FindBy(xpath = "//a[@href='/wishlist']")
    public WebElement wishlist;

    @FindBy(xpath = "//div[@class='header-links'] //a[@href='/customer/info']")
    public WebElement myAccount;

    @FindBy(xpath = "//a[@href='/logout']")
    public WebElement logout;

    public List<WebElement> getHeaderItemNotLoggedIn(){
        List<WebElement> headerItems = new ArrayList<>();

        headerItems.add(register);
        headerItems.add(login);
        headerItems.add(wishlist);
        headerItems.add(myAccount);

        return headerItems;
    }

    // TODO to be moved
    public void verifyTopBarNotLoggedIn(){
        softAssert.assertTrue(register.isDisplayed());
        softAssert.assertTrue(login.isDisplayed());
        softAssert.assertTrue(cart.isDisplayed());
        softAssert.assertTrue(wishlist.isDisplayed());

        softAssert.assertAll();
    }

}

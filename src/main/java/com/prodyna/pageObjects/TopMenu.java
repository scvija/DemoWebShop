package com.prodyna.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;


public class TopMenu {

    public WebDriver driver;
    public SoftAssert softAssert;

    public TopMenu(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

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


}

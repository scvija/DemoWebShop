package com.prodyna.pageObjects;

import com.prodyna.configuration.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class Search extends BaseTest {
    public WebDriver driver;
    public SoftAssert softAssert = new SoftAssert();

    public Search(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "small-searchterms")
    public WebElement headerSearch;

    @FindBy(xpath = "//input[@value='Search']")
    public WebElement headerSearchButton;
}

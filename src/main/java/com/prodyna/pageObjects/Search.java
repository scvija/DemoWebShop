package com.prodyna.pageObjects;

import com.prodyna.configuration.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class Search extends BaseTest {
    public WebDriver driver;
    public SoftAssert softAssert = new SoftAssert();
    public WebDriverWait wait;

    public Search(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "small-searchterms")
    public WebElement headerSearch;

    @FindBy(xpath = "//input[@value='Search']")
    public WebElement headerSearchButton;

    @FindBy(id = "ui-id-1")
    public WebElement headerSearchAutocompleteDialogue;

    @FindBy(xpath = "//ul[@id='ui-id-1']//li[1]")
    public WebElement headerSearchFirstRecommendation;

    @FindBy(xpath = "//label[@for='Q']")
    public WebElement advancedSearchFieldLabel;

    @FindBy(xpath = "//input[@id='Q']")
    public WebElement advancedSearchFieldInput;

    @FindBy(xpath = "//label[@for='As']")
    public WebElement advancedSearch;

    @FindBy(xpath = "//label[@for='Cid']")
    public WebElement categoryLabel;

    @FindBy(id = "Cid")
    public WebElement categoryDropdown;

    @FindBy(xpath = "//label[@for='Isc']")
    public WebElement autoSearchSubCategories;

    @FindBy(xpath = "//label[@for='Mid']")
    public WebElement manufacturerLabel;

    @FindBy(id = "Mid")
    public WebElement manufacturerDropdown;

    @FindBy(xpath = "//label[text() = 'Price range:']")
    public WebElement priceRangeLabel;

    @FindBy(id = "Pf")
    public WebElement priceFromInput;

    @FindBy(id = "Pt")
    public WebElement priceToInput;

    @FindBy(id = "Sid")
    public WebElement searchDescriptions;

    @FindBy(xpath = "input[class='button-1 search-button']")
    public  WebElement advancedSearchButton;



}

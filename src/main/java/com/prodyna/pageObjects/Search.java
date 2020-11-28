package com.prodyna.pageObjects;

import com.prodyna.configuration.BasePageConfiguration;
import com.prodyna.configuration.BaseTestConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class Search extends BasePageConfiguration {
    public WebDriver driver;

    public Search(WebDriver driver) {
        super(driver);
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
    public WebElement advancedSearchCheckbox;

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

    @FindBy(xpath = "//input[@class='button-1 search-button']")
    public WebElement advancedSearchButton;

    @FindBy(className = "warning")
    public WebElement warning;

    @FindBy(className = "result")
    public WebElement result;

    @FindBy(className = "product-title")
    public WebElement productTileTitle;


}

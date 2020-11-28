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

    // SEARCH

    // HEADER SEARCH

    public static final String headerSearchStandardText = "Search store";
    public static final String searchTitle = "Search";
    public static final String searchShort = "ta";
    public static final String searchInvalid = "DoesNotExist";
    public static final String searchPartialValid = "boo";
    public static final String searchPartialInvalid = "booo";
    public static final String searchHealthBook = "Health Book";
    public static final String noSearchResults = "No products were found that matched your criteria.";
    public static final String minSearchLength =  "Search term minimum length is 3 characters";

    // ADVANCED SEARCH

    public static final String computerLower = "computer";
    public static final String computerCapital = "Computer";
    public static final String searchCategoryBooks = "Books";
    public static final String searchCategoryComputers = "Computers";
    public static final String searchCategoryDesktops = "Computers &gt;&gt; Desktops";
    public static final String searchCategoryNotebooks = "Computers &gt;&gt; Notebooks";
    public static final String searchCategoryAccessories = "Computers &gt;&gt; Accessories";
    public static final String searchCategoryElectronics = "Electronics";
    public static final String searchCategoryCamera = "Electronics &gt;&gt; Camera, photo";
    public static final String searchCategoryPhones = "Electronics &gt;&gt; Cell phones";
    public static final String searchCategoryShoes = "Apparel &amp; Shoes";
    public static final String searchCategoryDigital = "Digital downloads";
    public static final String searchCategoryJewelry = "Jewelry";
    public static final String searchCategoryGift = "Gift Cards";

    public static final String searchManufacturerAll = "All";
    public static final String searchManufacturerTricentis = "Tricentis";

    public static final String searchPriceHigh = "10000";
    public static final String searchPriceMiddle = "1000";
    public static final String searchPriceLow = "100";

    public static final String searchBookPartialDescription = "Worried about your health";
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

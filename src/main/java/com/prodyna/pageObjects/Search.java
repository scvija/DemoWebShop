package com.prodyna.pageObjects;

import com.prodyna.configuration.BasePageConfiguration;
import com.prodyna.configuration.BaseTestConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import static com.prodyna.utility.Constants.titleLocator;

public class Search extends BasePageConfiguration {
    public WebDriver driver;

    public Search(WebDriver driver) {
        super(driver);
    }

    // SEARCH

    // HEADER SEARCH

    public static final String HEADER_SEARCH_STANDARD_TEXT = "Search store";
    public static final String SEARCH_TITLE = "Search";
    public static final String SEARCH_SHORT = "ta";
    public static final String SEARCH_INVALID = "DoesNotExist";
    public static final String SEARCH_PARTIAL_VALID = "boo";
    public static final String SEARCH_PARTIAL_INVALID = "booo";
    public static final String SEARCH_HEALTH_BOOK = "Health Book";
    public static final String NO_SEARCH_RESULTS = "No products were found that matched your criteria.";
    public static final String MIN_SEARCH_LENGTH =  "Search term minimum length is 3 characters";

    // ADVANCED SEARCH

    public static final String COMPUTER_LOWER = "computer";
    public static final String COMPUTER_CAPITAL = "Computer";
    public static final String SEARCH_CATEGORY_BOOKS = "Books";
    public static final String SEARCH_CATEGORY_COMPUTERS = "Computers";
    public static final String SEARCH_CATEGORY_DESKTOPS = "Computers &gt;&gt; Desktops";
    public static final String SEARCH_CATEGORY_NOTEBOOKS = "Computers &gt;&gt; Notebooks";
    public static final String SEARCH_CATEGORY_ACCESSORIES = "Computers &gt;&gt; Accessories";
    public static final String SEARCH_CATEGORY_ELECTRONICS = "Electronics";
    public static final String SEARCH_CATEGORY_CAMERA = "Electronics &gt;&gt; Camera, photo";
    public static final String SEARCH_CATEGORY_PHONES = "Electronics &gt;&gt; Cell phones";
    public static final String SEARCH_CATEGORY_SHOES = "Apparel &amp; Shoes";
    public static final String SEARCH_CATEGORY_DIGITAL = "Digital downloads";
    public static final String SEARCH_CATEGORY_JEWELRY = "Jewelry";
    public static final String SEARCH_CATEGORY_GIFT = "Gift Cards";

    public static final String SEARCH_MANUFACTURER_ALL = "All";
    public static final String SEARCH_MANUFACTURER_TRICENTIS = "Tricentis";

    public static final String SEARCH_PRICE_HIGH = "10000";
    public static final String SEARCH_PRICE_MIDDLE = "1000";
    public static final String SEARCH_PRICE_LOW = "100";

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

    @FindBy(xpath = titleLocator)
    public WebElement pageTitle;

}

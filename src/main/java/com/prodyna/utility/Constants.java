package com.prodyna.utility;

public class Constants {

    // PAGES
    public static final String homepageUrl = "http://demowebshop.tricentis.com/";
    public static final String registerPageUrl = homepageUrl + "register";
    public static final String loginPageUrl = homepageUrl + "login";
    public static final String wishlistUrl = homepageUrl + "wishlist";
    public static final String searchUrl = homepageUrl + "search";

    // REGISTER USER DATA

    public static final String firstName = "Peter";
    public static final String lastName = "Peterson";
    public static final String emailConcat = "peter.peterson@gmail.com";
    public static final String emailInvalid = firstName + lastName;
    public static final String emailLogin = "peterpeterson@gmail.com";
    public static final String password = "Test123!";
    public static final String passwordShort = "test";
    public static final String passwordDifferent = "!123Test";

    // REGISTER FORM MESSAGES


    public static final String firstNameMandatory = "First name is required.";
    public static final String lastNameMandatory = "Last name is required.";
    public static final String emailMandatory = "Email is required.";
    public static final String emailWrongForm = "Wrong email";
    public static final String passwordMandatory = "Password is required.";
    public static final String passwordTooShort = "The password should have at least 6 characters.";
    public static final String passwordMissmatch = "The password and confirmation password do not match.";
    public static final String emailExists = "The specified email already exists";
    public static final String registrationComplete = "Your registration completed";

    // PRODUCTS PAGE

    // VALUES FOR SORTING DROPDOWNS

    public static final String sortPosition = "Position";
    public static final String sortNameAsc = "Name: A to Z";
    public static final String sortNameDesc = "Name: Z to A";
    public static final String sortLow = "Price: Low to High";
    public static final String sortHigh = "Price: High to Low";
    public static final String sortCreated = "Created on";

    // VALUES FOR ELEMENTS PER PAGE

    public static final String display4 = "4";
    public static final String display8 = "8";
    public static final String display12= "12";

    // VALUES FOR VIEW AS

    public static final String grid = "Grid";
    public static final String list = "List";


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




}

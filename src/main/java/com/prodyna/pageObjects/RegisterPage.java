package com.prodyna.pageObjects;

import com.prodyna.configuration.BaseTestConfiguration;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Data
public class RegisterPage extends BaseTestConfiguration {

    public WebDriver driver;
    public SoftAssert softAssert = new SoftAssert();

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    // REGISTER USER DATA

    public static final String FIRST_NAME = "Peter";
    public static final String LAST_NAME = "Peterson";
    public static final String EMAIL_CONCAT = "peter.peterson@gmail.com";
    public static final String EMAIL_INVALID = FIRST_NAME + LAST_NAME;
    public static final String EMAIL_VALID_LOGIN = "peterpeterson@gmail.com";
    public static final String PASSWORD_VALID = "Test123!";
    public static final String PASSWORD_SHORT = "test";
    public static final String PASSWORD_DIFFERENT = "!123Test";

    // REGISTER FORM MESSAGES


    public static final String FIRST_NAME_MANDATORY = "First name is required.";
    public static final String LAST_NAME_MANDATORY = "Last name is required.";
    public static final String EMAIL_MANDATORY = "Email is required.";
    public static final String EMAIL_WRONG_FORM = "Wrong email";
    public static final String PASSWORD_MANDATORY = "Password is required.";
    public static final String PASSWORD_TOO_SHORT = "The password should have at least 6 characters.";
    public static final String PASSWORD_MISSMATCH = "The password and confirmation password do not match.";
    public static final String EMAIL_EXISTS = "The specified email already exists";
    public static final String REGISTRATION_COMPLETE = "Your registration completed";


    @FindBy(xpath = "//strong[contains(text(),'Your Personal Details')]")
    public WebElement myPersonalDetails;

    @FindBy(xpath = "//strong[contains(text(),'Your Password')]")
    public WebElement yourPassword;

    @FindBy(xpath = "//label[contains(text(),'Gender:')]" )
    public WebElement genderLabel;

    @FindBy(id = "gender-male")
    public WebElement maleGender;

    @FindBy(id = "gender-male")
    public WebElement femaleGender;

    @FindBy(xpath = "//label[contains(text(),'First name:')]")
    public WebElement firstNameLabel;

    @FindBy(id = "FirstName")
    public WebElement firstNameInput;

    @FindBy(xpath = "//label[contains(text(),'Last name:')]")
    public WebElement lastNameLabel;

    @FindBy(id = "LastName")
    public WebElement lastnameInput;

    @FindBy(xpath = "//label[@for='Email']")
    public WebElement emailLabel;

    @FindBy(id = "Email")
    public WebElement emailInput;

    @FindBy(xpath = "//label[contains(text(),'Password:')]")
    public WebElement passwordLabel;

    @FindBy(id = "Password")
    public WebElement passwordInput;

    @FindBy(xpath = "//label[contains(text(),'Confirm password:')]")
    public WebElement confirmedPasswordLabel;

    @FindBy(id = "ConfirmPassword")
    public WebElement confirmedPasswordInput;

    @FindBy(id = "register-button")
    public WebElement registerButton;

    @FindBy(xpath = "//span[@data-valmsg-for='FirstName']")
    public WebElement firstNameMandatoryText;

    @FindBy(xpath = "//span[@data-valmsg-for='LastName']")
    public WebElement lastnameMandatoryText;

    @FindBy(xpath = "//span[@data-valmsg-for='Email']")
    public WebElement emailMandatoryText;

    @FindBy(xpath = "//span[@data-valmsg-for='Password']")
    public WebElement passwordMandatoryText;

    @FindBy(xpath = "//span[@data-valmsg-for='ConfirmPassword']")
    public WebElement confirmPasswordMandatoryText;

    @FindBy(xpath = "//span[@class='field-validation-error']")
    public List<WebElement> mandatoryTexts;

    @FindBy(xpath = "//div[@class='result']")
    public WebElement pageBody;

    @FindBy(xpath = "//input[@value='Continue']")
    public WebElement continueButton;

    @FindBy(className = "validation-summary-errors")
    public WebElement summaryErrorMessage;

    @FindBy(className = "result")
    public WebElement resultMessage;

}

package com.prodyna.pageObjects;

import com.prodyna.configuration.BasePageConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.prodyna.utility.Constants.titleLocator;

public class LoginPage extends BasePageConfiguration {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // LOGIN PAGE

    public static final String loginPageNewCustomerTitle = "New Customer";
    public static final String loginPageNewCustomerText = "By creating an account on our website you will be able to shop faster, be up to date on an orders status, and keep track of the orders you have previously made.";
    public static final String loginPageReturningCustomerTitle = "New Customer";
    public static final String loginPageAboutTitle = "About login / registration";
    public static final String loginPageAboutDescription = "Put your login / registration information here. You can edit this in the admin site.";
    public static final String loginPageReturningErrorMessageAccountNotFound = "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found";
    public static final String loginPageReturningErrorMessageInvalidCredentials = "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect";
    public static final String loginPageReturningEmailInvalidText = "Please enter a valid email address.";

    // PASSWORD RECOVERY PAGE

    public static final String passwordRecoveryTitle = "Password recovery";
    public static final String passwordRecoveryDescriptionText = "Please enter your email address below. You will receive a link to reset your password.";
    public static final String passwordRecoveryWrongEmailMessage = "Wrong email";
    public static final String passwordRecoveryEnterEmailMessage = "Enter your email";
    public static final String passwordRecoveryEmailSentSuccessMessageText = "Email with instructions has been sent to you.";


    @FindBy(xpath = "//div[@class='new-wrapper register-block']//div[@class='title']")
    public WebElement newCustomerTitle;

    @FindBy(xpath = "//div[@class='new-wrapper register-block']//div[@class='text']")
    public WebElement newCustomerText;

    @FindBy(xpath = "//input[@value='Register']")
    public WebElement newCustomerRegisterButton;

    @FindBy(xpath = "//div[@class='returning-wrapper']//div[@class='title']")
    public WebElement returningCustomerTitle;

    @FindBy(id = "Email")
    public WebElement returningEmailInput;

    @FindBy(xpath = "//span[@data-valmsg-for='Email']")
    public WebElement returningEmailValidationMessage;

    @FindBy(id = "Password")
    public WebElement returningPasswordInput;

    @FindBy(id = "RememberMe")
    public WebElement returningRememberMeCheckbox;

    @FindBy(xpath = "//a[@href='/passwordrecovery']")
    public WebElement forgotPassword;

    @FindBy(xpath = "//input[@value='Log in']")
    public WebElement returningLoginButton;

    @FindBy(className = "topic-html-content-header")
    public WebElement aboutLoginAndRegistrationTitle;

    @FindBy(className = "topic-html-content-body")
    public WebElement aboutLoginAndRegistrationDescription;

    @FindBy(className = "message-error")
    public WebElement returningErrorMessage;

    @FindBy(className = "tooltip")
    public WebElement passwordRecoveryDescription;

    @FindBy(id = "Email")
    public WebElement passwordRecoveryEmailInput;

    @FindBy(xpath = "//input[@value='Recover']")
    public WebElement passwordRecoveryRecoverButton;

    @FindBy(className = "result")
    public WebElement passwordRecoveryEmailSentMessage;

    @FindBy(xpath = "//span[@for='Email']")
    public WebElement passwordRecoveryEmailValidationMessage;

    @FindBy(xpath = titleLocator)
    public WebElement pageTitle;


}

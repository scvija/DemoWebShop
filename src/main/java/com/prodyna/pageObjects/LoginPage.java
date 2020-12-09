package com.prodyna.pageObjects;

import com.prodyna.configuration.BasePageConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static com.prodyna.utility.Constants.titleLocator;

public class LoginPage extends BasePageConfiguration {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // LOGIN PAGE

    public static final String LOGIN_PAGE_NEW_CUSTOMER_TITLE = "New Customer";
    public static final String LOGIN_PAGE_NEW_CUSTOMER_TEXT = "By creating an account on our website you will be able to shop faster, be up to date on an orders status, and keep track of the orders you have previously made.";
    public static final String LOGIN_PAGE_RETURNING_CUSTOMER_TITLE = "New Customer";
    public static final String LOGIN_PAGE_ABOUT_TITLE = "About login / registration";
    public static final String LOGIN_PAGE_ABOUT_DESCRIPTION = "Put your login / registration information here. You can edit this in the admin site.";
    public static final String LOGIN_PAGE_RETURNING_ERROR_MESSAGE_ACCOUNT_NOT_FOUND = "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found";
    public static final String LOGIN_PAGE_RETURNING_ERROR_MESSAGE_INVALID_CREDENTIALS = "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect";
    public static final String LOGIN_PAGE_RETURNING_EMAIL_INVALID_TEXT = "Please enter a valid email address.";

    // PASSWORD RECOVERY PAGE

    public static final String PASSWORD_RECOVERY_TITLE = "Password recovery";
    public static final String PASSWORD_RECOVERY_DESCRIPTION_TEXT = "Please enter your email address below. You will receive a link to reset your password.";
    public static final String PASSWORD_RECOVERY_WRONG_EMAIL_MESSAGE = "Wrong email";
    public static final String PASSWORD_RECOVERY_ENTER_EMAIL_MESSAGE = "Enter your email";
    public static final String PASSWORD_RECOVERY_EMAIL_SENT_SUCCESS_MESSAGE_TEXT = "Email with instructions has been sent to you.";


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

    public List<WebElement> getLoginElements(){
        List<WebElement> elements = new ArrayList<>();

        elements.add(pageTitle);
        elements.add(newCustomerTitle);
        elements.add(newCustomerText);
        elements.add(newCustomerRegisterButton);
        elements.add(returningCustomerTitle);
        elements.add(returningCustomerTitle);
        elements.add(returningPasswordInput);
        elements.add(returningRememberMeCheckbox);
        elements.add(returningCustomerTitle);
        elements.add(returningLoginButton);
        elements.add(aboutLoginAndRegistrationTitle);
        elements.add(aboutLoginAndRegistrationDescription);

        return elements;
    }
}

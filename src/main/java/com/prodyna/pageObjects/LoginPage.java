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

package com.prodyna.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    @FindBy(className = "forgot-password")
    public WebElement forgotPassword;

    @FindBy(xpath = "//input[@value='Log in']")
    public WebElement returningLoginButton;

    @FindBy(className = "topic-html-content-header")
    public WebElement aboutLoginAndRegistrationTitle;

    @FindBy(className = "topic-html-content-body")
    public WebElement aboutLoginAndRegistrationDescription;

    @FindBy(className = "message-error")
    public WebElement returningErrorMessage;

    public void loginWithCredentials(String email, String password){
        returningEmailInput.clear();
        returningEmailInput.sendKeys(email);

        returningPasswordInput.clear();
        returningPasswordInput.sendKeys(password);

        returningLoginButton.click();

    }

}

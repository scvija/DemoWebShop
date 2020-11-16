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
    WebElement newCustomerTitle;

    @FindBy(xpath = "//div[@class='new-wrapper register-block']//div[@class='text']")
    WebElement newCustomerText;

    @FindBy(className = "button-1 register-button")
    WebElement newCustomerRegisterButton;

    @FindBy(xpath = "//div[@class='returning-wrapper']//div[@class='title']")
    WebElement ReturningCustomerTitle;

    @FindBy(id = "Email")
    WebElement returningEmailInput;

    @FindBy(id = "Password")
    WebElement returningPasswordInput;

    @FindBy(id = "RememberMe")
    WebElement rememberMeCheckbox;

    @FindBy(className = "forgot-password")
    WebElement forgotPassword;

    @FindBy(className = "button-1 login-button")
    WebElement returningLoginButton;

    @FindBy(className = "topic-html-content-header")
    WebElement aboutLoginAndRegistrationTitle;

    @FindBy(className = "topic-html-content-body")
    WebElement aboutLoginAndRegistrationDescription;


}

package com.prodyna.services;

import com.prodyna.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class LoginService extends SeleniumService{

    public LoginService(WebDriver driver) {
        super(driver);
    }


    public void loginWithCredentials(String email, String password){
        LoginPage login = new LoginPage(driver);

        login.returningEmailInput.clear();
        login.returningEmailInput.sendKeys(email);

        login.returningPasswordInput.clear();
        login.returningPasswordInput.sendKeys(password);

        login.returningLoginButton.click();

    }

    public void verifyLoginElementsAreDisplayed() {
        LoginPage login = new LoginPage(driver);

        softAssert.assertTrue(isElementDisplayed(login.pageTitle));

        softAssert.assertTrue(isElementDisplayed(login.newCustomerTitle));
        softAssert.assertTrue(isElementDisplayed(login.newCustomerText));
        softAssert.assertTrue(isElementDisplayed(login.newCustomerRegisterButton));

        softAssert.assertTrue(isElementDisplayed(login.returningCustomerTitle));
        softAssert.assertTrue(isElementDisplayed(login.returningCustomerTitle));
        softAssert.assertTrue(isElementDisplayed(login.returningPasswordInput));
        softAssert.assertTrue(isElementDisplayed(login.returningRememberMeCheckbox));
        softAssert.assertTrue(isElementDisplayed(login.returningCustomerTitle));
        softAssert.assertTrue(isElementDisplayed(login.returningLoginButton));

        softAssert.assertTrue(isElementDisplayed(login.aboutLoginAndRegistrationTitle));
        softAssert.assertTrue(isElementDisplayed(login.aboutLoginAndRegistrationDescription));

        softAssert.assertAll();
    }
}

package com.prodyna.services;

import com.prodyna.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import static com.prodyna.pageObjects.LoginPage.PASSWORD_RECOVERY_DESCRIPTION_TEXT;
import static com.prodyna.pageObjects.LoginPage.PASSWORD_RECOVERY_TITLE;
import static com.prodyna.pageObjects.RegisterPage.EMAIL_VALID_LOGIN;

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
        AssertService assertService = new AssertService(driver);

        assertService.assertElementsAreDisplayed(login.getLoginElements());

    }

    public void verifyPasswordRecoveryElements(){
        LoginPage login = new LoginPage(driver);
        AssertService assertService = new AssertService(driver);

        assertService.assertElementTextEqualsText(login.pageTitle, PASSWORD_RECOVERY_TITLE);
        assertService.assertElementTextEqualsText(login.passwordRecoveryDescription, PASSWORD_RECOVERY_DESCRIPTION_TEXT);

        assertService.assertElementIsDisplayed(login.passwordRecoveryEmailInput);
        assertService.assertElementIsDisplayed(login.passwordRecoveryRecoverButton);
    }

    public void sendPasswordRecoveryEmail(String email){
        LoginPage login = new LoginPage(driver);

        enterText(login.passwordRecoveryEmailInput, email);
        clickElement(login.passwordRecoveryRecoverButton);
    }
}

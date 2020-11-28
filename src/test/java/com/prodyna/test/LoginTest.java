package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.Header;
import com.prodyna.pageObjects.LoginPage;
import com.prodyna.pageObjects.ProductPage;
import com.prodyna.services.LoginService;
import com.prodyna.services.SeleniumService;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.prodyna.utility.Constants.*;

public class LoginTest extends BaseTestConfiguration {

    @Test
    public void loginPageLayout(){
        SeleniumService seleniumService = new SeleniumService(driver);
        Header header = new Header(driver);
        LoginPage login = new LoginPage(driver);
        LoginService loginService = new LoginService(driver);

        seleniumService.navigateToPage(loginPageUrl);

        seleniumService.clickElement(header.login);

        loginService.verifyLoginElementsAreDisplayed();
    }

    @Test
    public void loginPageRegisterButtonTest(){
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);

        seleniumService.navigateToPage(loginPageUrl);

        seleniumService.clickElement(login.newCustomerRegisterButton);
        softAssert.assertEquals(login.pageTitle.getText(), "Register");

        softAssert.assertAll();
    }


    @Test
    public void loginPageLoginHappyPathTest(){
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);
        LoginService loginService = new LoginService(driver);
        Header header = new Header(driver);

        seleniumService.navigateToPage(loginPageUrl);

        // TODO loginmethod? where to store

        loginService.loginWithCredentials(emailValidLogin, passwordValid);
        softAssert.assertTrue(seleniumService.compareElementTextWithExpected(header.myAccount, emailValidLogin));

        softAssert.assertAll();
    }


    @Test
    public void loginPageLoginNegativeTest(){
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);
        LoginService loginService = new LoginService(driver);

        seleniumService.navigateToPage(loginPageUrl);

        seleniumService.clickElement(login.returningLoginButton);
        softAssert.assertEquals(login.returningErrorMessage.getText(), loginPageReturningErrorMessageAccountNotFound);

        loginService.loginWithCredentials(emailInvalid, passwordValid);
        softAssert.assertEquals(login.returningEmailValidationMessage.getText(), loginPageReturningEmailInvalidText);

        loginService.loginWithCredentials(emailValidLogin, passwordDifferent);
        softAssert.assertEquals(login.returningErrorMessage.getText(), loginPageReturningErrorMessageInvalidCredentials);

        softAssert.assertAll();
    }

    @Test
    public void loginPageForgotPasswordLayoutTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);
        ProductPage product = new ProductPage(driver);

        seleniumService.navigateToPage(loginPageUrl);

        seleniumService.clickElement(login.forgotPassword);

        softAssert.assertEquals(login.pageTitle.getText(), passwordRecoveryTitle);
        softAssert.assertEquals(login.passwordRecoveryDescription.getText(), passwordRecoveryDescription);

        softAssert.assertTrue(seleniumService.isElementDisplayed(login.passwordRecoveryEmailInput));
        softAssert.assertTrue(seleniumService.isElementDisplayed(login.passwordRecoveryRecoverButton));

        softAssert.assertAll();

    }

    @Test
    public void LoginPagePasswordRecoveryHappyPathTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);

        seleniumService.navigateToPage(passwordRecoveryUrl);

        seleniumService.enterText(login.passwordRecoveryEmailInput, emailValidLogin);
        seleniumService.clickElement(login.passwordRecoveryRecoverButton);
        softAssert.assertEquals(login.passwordRecoveryEmailSentMessage.getText(), passwordRecoveryEmailSentSuccessMessageText);

        softAssert.assertAll();
    }

    @Test
    public void LoginPagePasswordRecoveryNegativeTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);

        seleniumService.navigateToPage(passwordRecoveryUrl);

        seleniumService.clickElement(login.passwordRecoveryRecoverButton);
        softAssert.assertEquals(login.passwordRecoveryEmailValidationMessage.getText(), passwordRecoveryEnterEmailMessage);

        seleniumService.enterText(login.passwordRecoveryEmailInput, emailInvalid);
        softAssert.assertEquals(login.passwordRecoveryEmailValidationMessage.getText(), passwordRecoveryWrongEmailMessage);

        softAssert.assertAll();
    }


}

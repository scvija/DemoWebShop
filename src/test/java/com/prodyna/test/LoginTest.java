package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.Header;
import com.prodyna.pageObjects.LoginPage;
import com.prodyna.pageObjects.ProductPage;
import com.prodyna.services.LoginService;
import com.prodyna.services.SeleniumService;
import org.testng.annotations.Test;

import static com.prodyna.pageObjects.LoginPage.*;
import static com.prodyna.pageObjects.RegisterPage.*;
import static com.prodyna.utility.Constants.loginPageUrl;
import static com.prodyna.utility.Constants.passwordRecoveryUrl;


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

        // TODO loginMethod? where to store

        loginService.loginWithCredentials(EMAIL_VALID_LOGIN, PASSWORD_VALID);
        softAssert.assertTrue(seleniumService.compareElementTextWithExpected(header.myAccount, EMAIL_VALID_LOGIN));

        softAssert.assertAll();
    }


    @Test
    public void loginPageLoginNegativeTest(){
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);
        LoginService loginService = new LoginService(driver);

        seleniumService.navigateToPage(loginPageUrl);

        seleniumService.clickElement(login.returningLoginButton);
        softAssert.assertEquals(login.returningErrorMessage.getText(), LOGIN_PAGE_RETURNING_ERROR_MESSAGE_ACCOUNT_NOT_FOUND);

        loginService.loginWithCredentials(EMAIL_INVALID, PASSWORD_VALID);
        softAssert.assertEquals(login.returningEmailValidationMessage.getText(), LOGIN_PAGE_RETURNING_EMAIL_INVALID_TEXT);

        loginService.loginWithCredentials(EMAIL_VALID_LOGIN, PASSWORD_DIFFERENT);
        softAssert.assertEquals(login.returningErrorMessage.getText(), LOGIN_PAGE_RETURNING_ERROR_MESSAGE_INVALID_CREDENTIALS);

        softAssert.assertAll();
    }

    @Test
    public void loginPageForgotPasswordLayoutTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);
        ProductPage product = new ProductPage(driver);

        seleniumService.navigateToPage(loginPageUrl);

        seleniumService.clickElement(login.forgotPassword);

        softAssert.assertEquals(login.pageTitle.getText(), PASSWORD_RECOVERY_TITLE);
        softAssert.assertEquals(login.passwordRecoveryDescription.getText(), PASSWORD_RECOVERY_DESCRIPTION_TEXT);

        softAssert.assertTrue(seleniumService.isElementDisplayed(login.passwordRecoveryEmailInput));
        softAssert.assertTrue(seleniumService.isElementDisplayed(login.passwordRecoveryRecoverButton));

        softAssert.assertAll();

    }

    @Test
    public void LoginPagePasswordRecoveryHappyPathTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);

        seleniumService.navigateToPage(passwordRecoveryUrl);

        seleniumService.enterText(login.passwordRecoveryEmailInput, EMAIL_VALID_LOGIN);
        seleniumService.clickElement(login.passwordRecoveryRecoverButton);
        softAssert.assertEquals(login.passwordRecoveryEmailSentMessage.getText(), PASSWORD_RECOVERY_EMAIL_SENT_SUCCESS_MESSAGE_TEXT);

        softAssert.assertAll();
    }

    @Test
    public void LoginPagePasswordRecoveryNegativeTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);

        seleniumService.navigateToPage(passwordRecoveryUrl);

        seleniumService.clickElement(login.passwordRecoveryRecoverButton);
        softAssert.assertEquals(login.passwordRecoveryEmailValidationMessage.getText(), PASSWORD_RECOVERY_ENTER_EMAIL_MESSAGE);

        seleniumService.enterText(login.passwordRecoveryEmailInput, EMAIL_INVALID);
        softAssert.assertEquals(login.passwordRecoveryEmailValidationMessage.getText(), PASSWORD_RECOVERY_WRONG_EMAIL_MESSAGE);

        softAssert.assertAll();
    }


}

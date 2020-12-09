package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.Header;
import com.prodyna.pageObjects.LoginPage;
import com.prodyna.services.AssertService;
import com.prodyna.services.LoginService;
import com.prodyna.services.SeleniumService;
import org.testng.annotations.Test;

import static com.prodyna.pageObjects.LoginPage.*;
import static com.prodyna.pageObjects.RegisterPage.*;
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
    public void loginPageRegisterButtonTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(loginPageUrl);

        seleniumService.clickElement(login.newCustomerRegisterButton);
        assertService.assertUrlEqualsToExpected(registerPageUrl);

    }


    @Test
    public void loginPageLoginHappyPathTest(){
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginService loginService = new LoginService(driver);
        Header header = new Header(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(loginPageUrl);

        loginService.loginWithCredentials(EMAIL_VALID_LOGIN, PASSWORD_VALID);
        assertService.assertElementTextEqualsText(header.myAccount, EMAIL_VALID_LOGIN);
    }


    @Test
    public void loginPageLoginNegativeTest(){
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);
        LoginService loginService = new LoginService(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(loginPageUrl);

        seleniumService.clickElement(login.returningLoginButton);
        assertService.assertElementTextEqualsText(login.returningErrorMessage, LOGIN_PAGE_RETURNING_ERROR_MESSAGE_ACCOUNT_NOT_FOUND);

        loginService.loginWithCredentials(EMAIL_INVALID, PASSWORD_VALID);
        assertService.assertElementTextEqualsText(login.returningEmailValidationMessage, LOGIN_PAGE_RETURNING_EMAIL_INVALID_TEXT);

        loginService.loginWithCredentials(EMAIL_VALID_LOGIN, PASSWORD_DIFFERENT);
        assertService.assertElementTextEqualsText(login.returningErrorMessage, LOGIN_PAGE_RETURNING_ERROR_MESSAGE_INVALID_CREDENTIALS);
    }

    @Test
    public void loginPageForgotPasswordLayoutTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);
        LoginService loginService = new LoginService(driver);

        seleniumService.navigateToPage(loginPageUrl);

        seleniumService.clickElement(login.forgotPassword);
        loginService.verifyPasswordRecoveryElements();

    }
    // TODO two remaining tests
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

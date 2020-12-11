package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.Header;
import com.prodyna.pageObjects.LoginPage;
import com.prodyna.services.AssertService;
import com.prodyna.services.LoginService;
import com.prodyna.services.SeleniumService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.prodyna.pageObjects.LoginPage.*;
import static com.prodyna.pageObjects.RegisterPage.*;
import static com.prodyna.utility.Constants.*;


public class LoginTest extends BaseTestConfiguration {


    @Test
    public void loginPageLayout() {

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

        Map<String,String> actualAndExpectedMessages = new HashMap<>();


        seleniumService.clickElement(login.returningLoginButton);
        actualAndExpectedMessages.put(login.returningErrorMessage.getText(), LOGIN_PAGE_RETURNING_ERROR_MESSAGE_ACCOUNT_NOT_FOUND);
// TODO separate single assert per test
        loginService.loginWithCredentials(EMAIL_INVALID, PASSWORD_VALID);
        actualAndExpectedMessages.put(login.returningEmailValidationMessage.getText(),LOGIN_PAGE_RETURNING_EMAIL_INVALID_TEXT );

        loginService.loginWithCredentials(EMAIL_VALID_LOGIN, PASSWORD_DIFFERENT);
        actualAndExpectedMessages.put(login.returningErrorMessage.getText(), LOGIN_PAGE_RETURNING_ERROR_MESSAGE_INVALID_CREDENTIALS);

        assertService.assertTextFromMap(actualAndExpectedMessages);
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
        AssertService assertService = new AssertService(driver);
        LoginService loginService = new LoginService(driver);

        seleniumService.navigateToPage(passwordRecoveryUrl);

        loginService.sendPasswordRecoveryEmail(EMAIL_VALID_LOGIN);
        assertService.assertElementTextEqualsText(login.passwordRecoveryEmailSentMessage,PASSWORD_RECOVERY_EMAIL_SENT_SUCCESS_MESSAGE_TEXT );

    }

    @Test
    public void LoginPagePasswordRecoveryNegativeTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        LoginPage login = new LoginPage(driver);
        LoginService loginService = new LoginService(driver);
        AssertService assertService = new AssertService(driver);


        seleniumService.navigateToPage(passwordRecoveryUrl);

        seleniumService.clickElement(login.passwordRecoveryRecoverButton);
        assertService.assertElementTextEqualsText(login.passwordRecoveryEmailValidationMessage, PASSWORD_RECOVERY_ENTER_EMAIL_MESSAGE);

        seleniumService.enterText(login.passwordRecoveryEmailInput, EMAIL_INVALID);
        assertService.assertElementTextEqualsText(login.passwordRecoveryEmailValidationMessage,PASSWORD_RECOVERY_WRONG_EMAIL_MESSAGE);

    }


}

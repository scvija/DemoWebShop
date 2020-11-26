package com.prodyna.test;

import com.prodyna.configuration.BaseTest;
import com.prodyna.pageObjects.Header;
import com.prodyna.pageObjects.LoginPage;
import com.prodyna.pageObjects.ProductPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.prodyna.utility.Constants.*;

public class LoginTest extends BaseTest {


    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loginPageLayout(){
        Header header = new Header(driver);
        LoginPage login = new LoginPage(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(loginPageUrl);

        clickElement(header.login);
        softAssert.assertTrue(isElementDisplayed(products.pageTitle));

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

    @Test
    public void loginPageRegisterButtonTest(){
        LoginPage login = new LoginPage(driver);
        ProductPage products = new ProductPage(driver);

        navigateToPage(loginPageUrl);


        clickElement(login.newCustomerRegisterButton);
        softAssert.assertEquals(products.pageTitle.getText(), "Register");

        softAssert.assertAll();
    }


    @Test
    public void loginPageLoginHappyPathTest(){
        LoginPage login = new LoginPage(driver);
        Header header = new Header(driver);

        navigateToPage(loginPageUrl);

        // TODO loginmethod? where to store

        login.loginWithCredentials(emailValidLogin, passwordValid);
        softAssert.assertEquals(header.myAccount.getText(), emailValidLogin);

        softAssert.assertAll();
    }


    @Test
    public void loginPageLoginNegativeTest(){
        LoginPage login = new LoginPage(driver);

        navigateToPage(loginPageUrl);

        clickElement(login.returningLoginButton);
        softAssert.assertEquals(login.returningErrorMessage.getText(), loginPageReturningErrorMessageAccountNotFound);

        login.loginWithCredentials(emailInvalid, passwordValid);
        softAssert.assertEquals(login.returningEmailValidationMessage.getText(), loginPageReturningEmailInvalidText);

        login.loginWithCredentials(emailValidLogin, passwordDifferent);
        softAssert.assertEquals(login.returningErrorMessage.getText(), loginPageReturningErrorMessageInvalidCredentials);

        softAssert.assertAll();
    }

    @Test
    public void loginPageForgotPasswordLayoutTest() {
        LoginPage login = new LoginPage(driver);
        ProductPage product = new ProductPage(driver);

        navigateToPage(loginPageUrl);

        clickElement(login.forgotPassword);

        softAssert.assertEquals(product.pageTitle.getText(), passwordRecoveryTitle);
        softAssert.assertEquals(login.passwordRecoveryDescription.getText(), passwordRecoveryDescription);

        softAssert.assertTrue(isElementDisplayed(login.passwordRecoveryEmailInput));
        softAssert.assertTrue(isElementDisplayed(login.passwordRecoveryRecoverButton));

        softAssert.assertAll();

    }

    @Test
    public void LoginPagePasswordRecoveryHappyPathTest() {
        LoginPage login = new LoginPage(driver);

        navigateToPage(passwordRecoveryUrl);

        enterText(login.passwordRecoveryEmailInput, emailValidLogin);
        clickElement(login.passwordRecoveryRecoverButton);
        softAssert.assertEquals(login.passwordRecoveryEmailSentMessage.getText(), passwordRecoveryEmailSentSuccessMessageText);

        softAssert.assertAll();
    }

    @Test
    public void LoginPagePasswordRecoveryNegativeTest() {
        LoginPage login = new LoginPage(driver);

        navigateToPage(passwordRecoveryUrl);

        clickElement(login.passwordRecoveryRecoverButton);
        softAssert.assertEquals(login.passwordRecoveryEmailValidationMessage.getText(), passwordRecoveryEnterEmailMessage);

        enterText(login.passwordRecoveryEmailInput, emailInvalid);
        softAssert.assertEquals(login.passwordRecoveryEmailValidationMessage.getText(), passwordRecoveryWrongEmailMessage);

        softAssert.assertAll();
    }


}

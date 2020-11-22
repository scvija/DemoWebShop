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
        navigateToPage(loginPageUrl);
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

        clickElement(login.newCustomerRegisterButton);

        softAssert.assertEquals(products.pageTitle.getText(), "Register");

        softAssert.assertAll();
    }


    @Test
    public void loginPageLoginHappyPathTest(){
        LoginPage login = new LoginPage(driver);
        Header header = new Header(driver);

        // TODO loginmethod? where to store

        login.loginWithCredentials(emailValidLogin, passwordValid);

        softAssert.assertEquals(header.myAccount.getText(), emailValidLogin);

        softAssert.assertAll();
    }


    @Test
    public void loginPageLoginNegativeTest(){

        LoginPage login = new LoginPage(driver);




    }
}

package com.prodyna.test;

import com.prodyna.configuration.BasePage;
import com.prodyna.configuration.BaseTest;
import com.prodyna.pageObjects.Header;
import com.prodyna.pageObjects.RegisterPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.prodyna.pageObjects.utility.Constants.*;

public class RegistrationTest extends BaseTest {
    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
        driver.get(homepageUrl());
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void registerPageLayoutTest() {

        Header header = new Header(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        SoftAssert softAssert = new SoftAssert();

        clickElement(header.register);



        // trouble with the loop inside the method
        registerPage.verifyRegistrationFormFieldsAreDisplayed();

        softAssert.assertAll();

    }

    @Test
    public void registerMandatoryText() {
        Header header = new Header(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        clickElement(header.register);
        clickElement(registerPage.registerButton);
        registerPage.verifyRegistrationFormMandatoryText();

    }

    @Test
    public void registerFunctionalityHappyPathTest() {
        Header header = new Header(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        SoftAssert softAssert = new SoftAssert();
        // Could possibly be replaced with driver.get / navigate to url, to make the tests more atomic
        clickElement(header.register);
        clickElement(registerPage.registerButton);

        //Login with a set of data??

        enterText(registerPage.firstNameInput, firstName);
        enterText(registerPage.lastnameInput, lastName);

        long timeStamp = System.currentTimeMillis();
        String mailAddress = timeStamp + emailLogin;
        enterText(registerPage.emailInput, mailAddress);

        enterText(registerPage.passwordInput, password);
        enterText(registerPage.confirmedPasswordInput, password);

//      takes a long time to list through the elements
        registerPage.mandatoryTextNotDisplayed();

        clickElement(registerPage.registerButton);
        softAssert.assertEquals(registerPage.resultMessage.getText(), registrationComplete);
        clickElement(registerPage.continueButton);

        String myAccountText = header.myAccount.getText();
        softAssert.assertEquals(myAccountText, mailAddress);
    }

}

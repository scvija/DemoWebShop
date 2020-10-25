package com.prodyna.test;

import com.prodyna.configuration.BaseTest;
import com.prodyna.pageObjects.Header;
import com.prodyna.pageObjects.RegisterPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static com.prodyna.utility.Constants.*;

public class RegistrationTest extends BaseTest {
    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
        navigateToPage(registerPageUrl);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void registerPageLayoutTest() {

        RegisterPage register = new RegisterPage(driver);

        // trouble with the loop inside the method
        register.verifyRegistrationFormFieldsAreDisplayed();

        softAssert.assertAll();

    }

    @Test
    public void registerMandatoryText() {
        RegisterPage register = new RegisterPage(driver);

        clickElement(register.registerButton);
        register.verifyRegistrationFormMandatoryText();

    }

    @Test
    public void registerFunctionalityHappyPathTest() {
        Header header = new Header(driver);
        RegisterPage register = new RegisterPage(driver);

        clickElement(register.registerButton);

        long timeStamp = System.currentTimeMillis();
        String mailAddressAndTime = timeStamp + emailLogin;

        register.fillRegisterForm(firstName,lastName,mailAddressAndTime, password);
        clickElement(register.registerButton);

        softAssert.assertEquals(register.resultMessage.getText(), registrationComplete);
        clickElement(register.continueButton);

        String myAccountText = header.myAccount.getText();
        softAssert.assertEquals(myAccountText, mailAddressAndTime);

        softAssert.assertAll();
    }

    @Test
    public void emailInputNegativeTest(){
        RegisterPage register = new RegisterPage(driver);

        enterText(register.emailInput, emailInvalid);
        clickElement(register.registerButton);
        softAssert.assertEquals(register.emailMandatoryText.getText(),emailWrongForm);

        register.fillRegisterForm(firstName,lastName,emailLogin,password);
        clickElement(register.registerButton);

        softAssert.assertEquals(register.summaryErrorMessage.getText(),emailExists);

        softAssert.assertAll();
    }

    @Test
    public void passwordInputNegativeTest(){
        RegisterPage register = new RegisterPage(driver);

        enterText(register.passwordInput, passwordShort);
        clickElement(register.registerButton);
        softAssert.assertEquals(register.passwordMandatoryText.getText(),passwordTooShort);

        enterText(register.passwordInput,password);
        enterText(register.confirmedPasswordInput, passwordDifferent);
        clickElement(register.registerButton);
        softAssert.assertEquals(register.confirmPasswordMandatoryText.getText(),passwordMissmatch);

        softAssert.assertAll();

    }


}

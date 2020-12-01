package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.Header;
import com.prodyna.pageObjects.RegisterPage;
import com.prodyna.services.RegistrationService;
import com.prodyna.services.SeleniumService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.prodyna.pageObjects.RegisterPage.*;
import static com.prodyna.utility.Constants.*;

public class RegistrationTest extends BaseTestConfiguration {

    @Test
    public void registerPageLayoutTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        RegisterPage register = new RegisterPage(driver);
        RegistrationService registrationService = new RegistrationService(driver);

        seleniumService.navigateToPage(registerPageUrl);

        // trouble with the loop inside the method
        registrationService.verifyRegistrationFormFieldsAreDisplayed();

        softAssert.assertAll();

    }

    @Test
    public void registerMandatoryText() {
        SeleniumService seleniumService = new SeleniumService(driver);
        RegisterPage register = new RegisterPage(driver);
        RegistrationService registrationService = new RegistrationService(driver);

        seleniumService.navigateToPage(registerPageUrl);

        seleniumService.clickElement(register.registerButton);
        registrationService.verifyRegistrationFormMandatoryText();

    }

    @Test
    public void registerFunctionalityHappyPathTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        Header header = new Header(driver);
        RegisterPage register = new RegisterPage(driver);
        RegistrationService registrationService = new RegistrationService(driver);

        seleniumService.navigateToPage(registerPageUrl);

        seleniumService.clickElement(register.registerButton);

        long timeStamp = System.currentTimeMillis();
        String mailAddressAndTime = timeStamp + EMAIL_CONCAT;

        registrationService.fillRegisterForm(FIRST_NAME,LAST_NAME,mailAddressAndTime, PASSWORD_VALID);
        seleniumService.clickElement(register.registerButton);

        softAssert.assertEquals(register.resultMessage.getText(), REGISTRATION_COMPLETE);
        seleniumService.clickElement(register.continueButton);

        String myAccountText = header.myAccount.getText();
        softAssert.assertEquals(myAccountText, mailAddressAndTime);

        softAssert.assertAll();
    }

    @Test
    public void emailInputNegativeTest(){
        SeleniumService seleniumService = new SeleniumService(driver);
        RegisterPage register = new RegisterPage(driver);
        RegistrationService registrationService = new RegistrationService(driver);

        seleniumService.navigateToPage(registerPageUrl);

        seleniumService.enterText(register.emailInput, EMAIL_INVALID);
        seleniumService.clickElement(register.registerButton);
        softAssert.assertEquals(register.emailMandatoryText.getText(),EMAIL_WRONG_FORM);

        registrationService.fillRegisterForm(FIRST_NAME,LAST_NAME, EMAIL_VALID_LOGIN,PASSWORD_VALID);
        seleniumService.clickElement(register.registerButton);

        softAssert.assertEquals(register.summaryErrorMessage.getText(),EMAIL_EXISTS);

        softAssert.assertAll();
    }

    @Test
    public void passwordInputNegativeTest(){
        SeleniumService seleniumService = new SeleniumService(driver);
        RegisterPage register = new RegisterPage(driver);

        seleniumService.navigateToPage(registerPageUrl);

        seleniumService.enterText(register.passwordInput, PASSWORD_SHORT);
        seleniumService.clickElement(register.registerButton);
        softAssert.assertEquals(register.passwordMandatoryText.getText(),PASSWORD_TOO_SHORT);

        seleniumService.enterText(register.passwordInput,PASSWORD_VALID);
        seleniumService.enterText(register.confirmedPasswordInput, PASSWORD_DIFFERENT);

        seleniumService.clickElement(register.registerButton);
        softAssert.assertEquals(register.confirmPasswordMandatoryText.getText(),PASSWORD_MISSMATCH);

        softAssert.assertAll();

    }


}

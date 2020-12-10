package com.prodyna.test;

import com.prodyna.configuration.BaseTestConfiguration;
import com.prodyna.pageObjects.Header;
import com.prodyna.pageObjects.RegisterPage;
import com.prodyna.services.AssertService;
import com.prodyna.services.RegistrationService;
import com.prodyna.services.SeleniumService;
import org.testng.annotations.Test;

import static com.prodyna.pageObjects.RegisterPage.*;
import static com.prodyna.utility.Constants.*;

public class RegistrationTest extends BaseTestConfiguration {

    @Test
    public void registerPageLayoutTest() {
        SeleniumService seleniumService = new SeleniumService(driver);
        RegistrationService registrationService = new RegistrationService(driver);

        seleniumService.navigateToPage(registerPageUrl);
        registrationService.verifyRegistrationFormFieldsAreDisplayed();

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
        AssertService assertService = new AssertService(driver);


        seleniumService.navigateToPage(registerPageUrl);

        String mailAddressAndTime = System.currentTimeMillis() + EMAIL_CONCAT;
        registrationService.submitRegisterForm(FIRST_NAME,LAST_NAME,mailAddressAndTime, PASSWORD_VALID);

        assertService.assertElementTextEqualsText(register.resultMessage, REGISTRATION_COMPLETE);
        seleniumService.clickElement(register.continueButton);

        assertService.assertElementTextEqualsText(header.myAccount, mailAddressAndTime );
    }


    @Test
    public void emailInputNegativeTest(){
        SeleniumService seleniumService = new SeleniumService(driver);
        RegisterPage register = new RegisterPage(driver);
        RegistrationService registrationService = new RegistrationService(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(registerPageUrl);

        seleniumService.enterText(register.emailInput, EMAIL_INVALID);
        seleniumService.clickElement(register.registerButton);
        assertService.assertElementTextEqualsText(register.emailMandatoryText,EMAIL_WRONG_FORM);

        registrationService.submitRegisterForm(FIRST_NAME,LAST_NAME, EMAIL_VALID_LOGIN,PASSWORD_VALID);
        assertService.assertElementTextEqualsText(register.summaryErrorMessage,EMAIL_EXISTS);
    }

    @Test
    public void passwordInputNegativeTest(){
        SeleniumService seleniumService = new SeleniumService(driver);
        RegisterPage register = new RegisterPage(driver);
        AssertService assertService = new AssertService(driver);

        seleniumService.navigateToPage(registerPageUrl);

        seleniumService.enterText(register.passwordInput, PASSWORD_SHORT);
        seleniumService.clickElement(register.registerButton);
        assertService.assertElementTextEqualsText(register.passwordMandatoryText, PASSWORD_TOO_SHORT);

        seleniumService.enterText(register.passwordInput,PASSWORD_VALID);
        seleniumService.enterText(register.confirmedPasswordInput, PASSWORD_DIFFERENT);

        seleniumService.clickElement(register.registerButton);
        assertService.assertElementTextEqualsText(register.confirmPasswordMandatoryText, PASSWORD_MISSMATCH);

    }


}

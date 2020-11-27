package com.prodyna.services;

import com.prodyna.pageObjects.RegisterPage;
import jdk.vm.ci.code.Register;
import org.openqa.selenium.WebDriver;

public class RegistrationService extends  SeleniumService{

    public RegistrationService(WebDriver driver) {
        super(driver);
    }

    public void verifyRegistrationFormFieldsAreDisplayed(){
        RegisterPage register = new RegisterPage(driver);

        softAssert.assertTrue(register.myPersonalDetails.isDisplayed());
        softAssert.assertTrue(register.yourPassword.isDisplayed());

        softAssert.assertTrue(register.genderLabel.isDisplayed());
        softAssert.assertTrue(register.maleGender.isDisplayed());
        softAssert.assertTrue(register.femaleGender.isDisplayed());

        softAssert.assertTrue(register.firstNameLabel.isDisplayed());
        softAssert.assertTrue(register.firstNameInput.isDisplayed());

        softAssert.assertTrue(register.lastNameLabel.isDisplayed());
        softAssert.assertTrue(register.lastnameInput.isDisplayed());

        softAssert.assertTrue(register.passwordLabel.isDisplayed());
        softAssert.assertTrue(register.passwordInput.isDisplayed());

        softAssert.assertTrue(register.confirmedPasswordLabel.isDisplayed());
        softAssert.assertTrue(register.confirmedPasswordInput.isDisplayed());

        softAssert.assertTrue(register.registerButton.isDisplayed());

        softAssert.assertAll();
    }

    public void verifyRegistrationFormMandatoryText(){
        RegisterPage register = new RegisterPage(driver);

        softAssert.assertTrue(register.lastnameMandatoryText.isDisplayed());
        softAssert.assertTrue(register.emailMandatoryText.isDisplayed());
        softAssert.assertTrue(register.passwordMandatoryText.isDisplayed());
        softAssert.assertTrue(register.confirmPasswordMandatoryText.isDisplayed());

        softAssert.assertAll();
    }

    public void fillRegisterForm(String firstName, String lastName, String email, String password){
        RegisterPage register = new RegisterPage(driver);

        enterText(register.firstNameInput, firstName);
        enterText(register.lastnameInput, lastName);
        enterText(register.emailInput, email);
        enterText(register.passwordInput, password);
        enterText(register.confirmedPasswordInput, password);
    }
}

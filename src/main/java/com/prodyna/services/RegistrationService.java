package com.prodyna.services;

import com.prodyna.pageObjects.RegisterPage;
import org.openqa.selenium.WebDriver;

public class RegistrationService extends  SeleniumService{

    public RegistrationService(WebDriver driver) {
        super(driver);
    }

    public void verifyRegistrationFormFieldsAreDisplayed(){
        RegisterPage register = new RegisterPage(driver);
        AssertService assertService = new AssertService(driver);

        assertService.assertElementsAreDisplayed(register.getRegistrationFormElements());

    }

    public void verifyRegistrationFormMandatoryText(){
        RegisterPage register = new RegisterPage(driver);
        AssertService assertService = new AssertService(driver);

        assertService.assertElementsAreDisplayed(register.getMandatoryFieldText());

    }

    public void submitRegisterForm(String firstName, String lastName, String email, String password){
        RegisterPage register = new RegisterPage(driver);

        enterText(register.firstNameInput, firstName);
        enterText(register.lastnameInput, lastName);
        enterText(register.emailInput, email);
        enterText(register.passwordInput, password);
        enterText(register.confirmedPasswordInput, password);

        clickElement(register.registerButton);

    }
}

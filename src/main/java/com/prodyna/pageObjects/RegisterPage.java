package com.prodyna.pageObjects;

import com.prodyna.configuration.BaseTest;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Data
public class RegisterPage extends BaseTest {

    public WebDriver driver;
    public SoftAssert softAssert = new SoftAssert();

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//strong[contains(text(),'Your Personal Details')]")
    public WebElement myPersonalDetails;

    @FindBy(xpath = "//strong[contains(text(),'Your Password')]")
    public WebElement yourPassword;

    @FindBy(xpath = "//label[contains(text(),'Gender:')]" )
    public WebElement genderLabel;

    @FindBy(id = "gender-male")
    public WebElement maleGender;

    @FindBy(id = "gender-male")
    public WebElement femaleGender;

    @FindBy(xpath = "//label[contains(text(),'First name:')]")
    public WebElement firstNameLabel;

    @FindBy(id = "FirstName")
    public WebElement firstNameInput;

    @FindBy(xpath = "//label[contains(text(),'Last name:')]")
    public WebElement lastNameLabel;

    @FindBy(id = "LastName")
    public WebElement lastnameInput;

    @FindBy(xpath = "//label[@for='Email']")
    public WebElement emailLabel;

    @FindBy(id = "Email")
    public WebElement emailInput;

    @FindBy(xpath = "//label[contains(text(),'Password:')]")
    public WebElement passwordLabel;

    @FindBy(id = "Password")
    public WebElement passwordInput;

    @FindBy(xpath = "//label[contains(text(),'Confirm password:')]")
    public WebElement confirmedPasswordLabel;

    @FindBy(id = "ConfirmPassword")
    public WebElement confirmedPasswordInput;

    @FindBy(id = "register-button")
    public WebElement registerButton;

    @FindBy(xpath = "//span[@data-valmsg-for='FirstName']")
    public WebElement firstNameMandatoryText;

    @FindBy(xpath = "//span[@data-valmsg-for='LastName']")
    public WebElement lastnameMandatoryText;

    @FindBy(xpath = "//span[@data-valmsg-for='Email']")
    public WebElement emailMandatoryText;

    @FindBy(xpath = "//span[@data-valmsg-for='Password']")
    public WebElement passwordMandatoryText;

    @FindBy(xpath = "//span[@data-valmsg-for='ConfirmPassword']")
    public WebElement confirmPasswordMandatoryText;

    @FindBy(xpath = "//span[@class='field-validation-error']")
    public List<WebElement> mandatoryTexts;

    @FindBy(xpath = "//div[@class='result']")
    public WebElement pageBody;

    @FindBy(xpath = "//input[@value='Continue']")
    public WebElement continueButton;

    @FindBy(className = "validation-summary-errors")
    public WebElement summaryErrorMessage;

    @FindBy(className = "result")
    public WebElement resultMessage;


    public void verifyRegistrationFormFieldsAreDisplayed(){

        softAssert.assertTrue(myPersonalDetails.isDisplayed());
        softAssert.assertTrue(yourPassword.isDisplayed());


        softAssert.assertTrue(genderLabel.isDisplayed());
        softAssert.assertTrue(maleGender.isDisplayed());
        softAssert.assertTrue(femaleGender.isDisplayed());


        softAssert.assertTrue(firstNameLabel.isDisplayed());
        softAssert.assertTrue(firstNameInput.isDisplayed());

        softAssert.assertTrue(lastNameLabel.isDisplayed());
        softAssert.assertTrue(lastnameInput.isDisplayed());

        softAssert.assertTrue(passwordLabel.isDisplayed());
        softAssert.assertTrue(passwordInput.isDisplayed());

        softAssert.assertTrue(confirmedPasswordLabel.isDisplayed());
        softAssert.assertTrue(confirmedPasswordInput.isDisplayed());

        softAssert.assertTrue(registerButton.isDisplayed());

        softAssert.assertAll();
    }

    public void verifyRegistrationFormMandatoryText(){

        softAssert.assertTrue(lastnameMandatoryText.isDisplayed());
        softAssert.assertTrue(emailMandatoryText.isDisplayed());
        softAssert.assertTrue(passwordMandatoryText.isDisplayed());
        softAssert.assertTrue(confirmPasswordMandatoryText.isDisplayed());

        softAssert.assertAll();
    }

    public void fillRegisterForm(String firstName, String lastName, String email, String password){

        enterText(firstNameInput, firstName);
        enterText(lastnameInput, lastName);
        enterText(emailInput, email);
        enterText(passwordInput, password);
        enterText(confirmedPasswordInput, password);
    }
}

package com.prodyna.configuration;

import com.prodyna.services.SeleniumService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePageConfiguration {
    public WebDriver driver;
    public SeleniumService seleniumService;

    public BasePageConfiguration(WebDriver driver) {
        PageFactory.initElements(driver, this);
        seleniumService = new SeleniumService(driver);

    }
}

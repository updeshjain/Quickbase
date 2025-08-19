package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.generic.CommonLib;


public class LeftPane {
    WebDriver driver;
    CommonLib common = new CommonLib();

    
    @FindBy(xpath="//*[@class='linkListItems-nav-v2']//*[text()='App settings']")
    private WebElement ssoNoBtn;
    
    @FindBy(xpath="//input[@name='loginid']")
    private WebElement usernameTxt;

    @FindBy(xpath="//input[@name='password']")
    private WebElement passwordTxt;

    @FindBy(xpath="//button[@name='SignIn']")
    private WebElement loginBtn;

    public LeftPane(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToAppPage(String appPageName) {
    	String AppPageXpath = String.format("//*[@class='linkListItems-nav-v2']//*[text()='%s']", appPageName);
    	driver.findElement(By.xpath(AppPageXpath)).click();
    	
    }

}

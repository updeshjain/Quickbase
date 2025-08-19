package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.generic.CommonLib;


public class AppSettingsPage {
    WebDriver driver;
    CommonLib common = new CommonLib();

    
    @FindBy(xpath="//a[@id='appSettingsNav_tables']")
    private WebElement tables;
    
    @FindBy(xpath="//input[@name='loginid']")
    private WebElement usernameTxt;

    @FindBy(xpath="//input[@name='password']")
    private WebElement passwordTxt;

    @FindBy(xpath="//button[@name='SignIn']")
    private WebElement loginBtn;

    public AppSettingsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void NavigatetoTablesPage() {

    	common.click(tables);
      //  GmailOtpWaiter.gmailClass();
        
    }
    


}

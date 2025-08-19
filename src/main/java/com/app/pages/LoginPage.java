package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.app.util.*;
import org.apache.logging.log4j.Logger;
import com.app.generic.CommonLib;



public class LoginPage {
    WebDriver driver;
    private Logger logger = Log.getLogger(LoginPage.class);
    CommonLib common = new CommonLib();

    
    @FindBy(xpath="//button[@id='quickbaseSignin']")
    private WebElement ssoNoBtn;
    
    @FindBy(xpath="//input[@name='loginid']")
    private WebElement usernameTxt;

    @FindBy(xpath="//input[@name='password']")
    private WebElement passwordTxt;

    @FindBy(xpath="//button[@name='SignIn']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginToApp(String user, String pass) throws InterruptedException {
    	common.click(ssoNoBtn);
        common.enterText(usernameTxt, user);
        common.enterText(passwordTxt, pass);
        common.click(loginBtn);
        logger.info("on login screen");
        /* After click on login button, It ask for security code which has been handled in below method, It has been commented as there we need to enter Gmail username and password
        Will enter the code through console */
        //  GmailOtpWaiter.gmailClass();
        /*adding sleep here to enter code through console*/
        Thread.sleep(10000);
    }
    


}

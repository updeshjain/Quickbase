package com.quickbase.projectmanagement.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.quickbase.projectmanagement.generic.CommonLib;
import com.quickbase.projectmanagement.util.*;
import org.apache.logging.log4j.Logger;

/*********************************
 *  Page class for login page, which includes
 * - SSO Page
 * - Main login page
 * - Authentication page
 * This class has all the methods and webelements needed
 * to login to the application
 */

public class LoginPage {
	WebDriver driver;
	private Logger logger = Log.getLogger(LoginPage.class);
	CommonLib common = new CommonLib();

	@FindBy(id = "quickbaseSignin")
	private WebElement ssoNoBtn;

	@FindBy(name = "loginid")
	private WebElement usernameTxt;

	@FindBy(name = "password")
	private WebElement passwordTxt;

	@FindBy(name = "SignIn")
	private WebElement loginBtn;
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void loginToApp(String user, String pass) throws InterruptedException {
		logger.info("Clicking on SSO No Button");
		common.click(ssoNoBtn);

		logger.info("Entering UserName");
		common.enterText(usernameTxt, user);

		logger.info("Entering Password");
		common.enterText(passwordTxt, pass);

		logger.info("Clicking on login button");
		common.click(loginBtn);

		logger.info("Getting security code from Gmail");
		/*
		 * After click on login button, It ask for security code which has been handled
		 * in below method, It has been commented, as user needs to enter Gmail username
		 * and password Will enter the code manually for demo purpose
		 */
		// GmaiUtility.readSecurityCodefromMail();

		/* adding sleep here to enter code manually */
		Thread.sleep(20000);
	}
}

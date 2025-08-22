package com.quickbase.projectmanagement.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.quickbase.projectmanagement.generic.CommonLib;
import com.quickbase.projectmanagement.util.Log;

/*********************************
 * Page class for Left pane , where 3 main menu available ]
 * - App Home 
 * - User 
 * - App Settings 
 * - Tables short cuts 
 * This class has all the methods to select any menu option from left pane
 */

public class LeftPane {
	WebDriver driver;
	private Logger logger = Log.getLogger(LoginPage.class);
	CommonLib common = new CommonLib();

	@FindBy(xpath = "//a[@data-test-id='link-app-settings']")
	private WebElement appSettingsMenu;

	public LeftPane(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// This method clicks on 'App Settings' menu option from left pane
	// @return AppSettingsPage

	public AppSettingsPage navigateToAppSettingsPage() {

		logger.info("clicking on app Settings link from left pane");
		common.click(appSettingsMenu);
		return new AppSettingsPage(driver);
	}
}
package com.quickbase.projectmanagement.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.quickbase.projectmanagement.generic.CommonLib;
import com.quickbase.projectmanagement.util.Log;

/*
 * Page class for App Settings Page This class has all the webElements and
 * methods required to perform different actions on App Setting page
 */

public class AppSettingsPage {
	WebDriver driver;
	private Logger logger = Log.getLogger(LoginPage.class);
	CommonLib common = new CommonLib();

	@FindBy(id ="appSettingsNav_tables")
	private WebElement tables;

	public AppSettingsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// This method clicks on 'Tables' link on App Settings page
	// @return TablesPage

	@SuppressWarnings("rawtypes")
	public TablesPage NavigatetoTablesPage() {

		logger.info("Clicking on Tables page link on App settings page");
		common.click(tables);
		return new TablesPage(driver);
	}
}
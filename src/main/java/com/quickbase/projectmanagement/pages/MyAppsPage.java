package com.quickbase.projectmanagement.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.quickbase.projectmanagement.generic.CommonLib;
import com.quickbase.projectmanagement.util.Log;

/************************************
 * This class contains all the elements of My Apps page and method to select
 * required app
 */
public class MyAppsPage {
	WebDriver driver;
	private Logger logger = Log.getLogger(LoginPage.class);
	CommonLib common = new CommonLib();

	public MyAppsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// select required app
	// @param appName : appname to select
	public void selectApp(String appName) {
		logger.info("Clicking on project:" + appName);
		String appNameXpath = String.format("//p[text()='%s']", appName);
		driver.findElement(By.xpath(appNameXpath)).click();
	}
}

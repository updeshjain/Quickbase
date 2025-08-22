package com.projectmanagement.TablePage.test;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.quickbase.projectmanagement.generic.BaseClass;
import com.quickbase.projectmanagement.pages.*;
import com.quickbase.projectmanagement.util.TestDataUtils;

public class TablePageTest extends BaseClass { // extend BaseTest

	String tableName = RandomStringUtils.randomAlphanumeric(5) + " " + "Table";
	String tableComment = "Comment for " + tableName;
	int NumberOfDefaultFields = 5;

	
	// E2E test to create new table from scratch and add labels to it.
	@SuppressWarnings("rawtypes")
	@Test(priority = 1, dependsOnMethods = {"Test"}, groups = {"E2E"})
	public void TablePage_E2E_Test() throws Exception {
		LoginPage lp = new LoginPage(driver); // driver inherited from BaseTest

		// login to the Application
		lp.loginToApp(TestDataUtils.getProperty("username"), TestDataUtils.getProperty("password"));
		MyAppsPage map = new MyAppsPage(driver);

		// Select specific app for My Apps page
		map.selectApp("Simple Project Manager (Updesh Jain)");

		String title = driver.getTitle();
		System.out.println("Page Title: " + title);
		// Assert.assertEquals(title, "expected Title");

		// Navigate to app Settings page by selecting App Settings option from left pane
		LeftPane leftPane = new LeftPane(driver);
		leftPane.navigateToAppSettingsPage();

		// Click on Tables link for App Settings page
		AppSettingsPage appSettingsPage = new AppSettingsPage(driver);
		appSettingsPage.NavigatetoTablesPage();

		// Create table from scratch by adding additional fields
		TablesPage tablesPage = new TablesPage(driver);
		int numberOfFields = tablesPage.createNewTable(tableName, tableComment, "lable1", "lable2");
		Thread.sleep(2000); // need to settle down the page

		// Navigate to Tables page by navigating to App Settings page
		leftPane = new LeftPane(driver);
		appSettingsPage = leftPane.navigateToAppSettingsPage();
		tablesPage = appSettingsPage.NavigatetoTablesPage();
		
		// Verify if Newly created table is showing in Tables page grid with correct number of fields
		@SuppressWarnings("unchecked")
		List<String> tableNames = tablesPage.getListofTablesFromGrid();
		Assert.assertTrue(tableNames.contains(tableName), tableName + " created successfully");
		Assert.assertEquals(tablesPage.getNumberOfFiledsForATable(tableName), NumberOfDefaultFields + numberOfFields,
				"Number of fileds are " + NumberOfDefaultFields + numberOfFields);
		
		// Delete the created table for test data cleaning up purpose
		tablesPage.deleteTable(tableName);
		
	}
	
	
	// Added as dependsonmethod enabler
	@Test()
	public void Test() {
		
	}
	@Override
	protected void beforeTestActions() {
		// login DB

	}

	@Override
	protected void afterTestActions() {
		// Logout DB

	}
}

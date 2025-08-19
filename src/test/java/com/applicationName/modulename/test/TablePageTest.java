package com.applicationName.modulename.test;

import org.testng.annotations.Test;

import com.app.generic.BaseTest;
import com.app.pages.*;
import com.app.util.TestData;

public class TablePageTest extends BaseTest {  // 👈 extend BaseTest

	String AppSeetingPage = "App settings";
	
	
    @Test
    public void TablePage_E2E_Test() throws Exception {
        LoginPage lp = new LoginPage(driver); // driver inherited from BaseTest
        lp.loginToApp(TestData.getProperty("username"), TestData.getProperty("password"));
        MyAppsPage map = new MyAppsPage(driver);
        map.selectApp("Simple Project Manager (Updesh Jain)");
        // Example assertion (SauceDemo)
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);
        // Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        LeftPane leftPane = new LeftPane(driver);
        leftPane.navigateToAppPage(AppSeetingPage);
        
        AppSettingsPage appSettingsPage = new AppSettingsPage(driver);
        appSettingsPage.NavigatetoTablesPage();
        
        
    }

	@Override
	protected void beforeTestActions() {
		// login  DB
		
	}

	@Override
	protected void afterTestActions() {
		// Logout DB
		
	}
}

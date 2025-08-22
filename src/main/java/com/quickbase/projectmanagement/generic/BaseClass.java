package com.quickbase.projectmanagement.generic;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.quickbase.projectmanagement.util.ScreenshotUtils;
import com.quickbase.projectmanagement.util.TestDataUtils;

/**************************************
 * This class contains all the before and after methods
 *****************************************/

public abstract class BaseClass {
	public static WebDriver driver;

	@BeforeMethod
	public void setUp() throws Exception {
		String browser = TestDataUtils.getProperty("browser");
		String url = TestDataUtils.getProperty("url");

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		// We can add more browsers as required

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.takeScreenshot(driver, result.getName());
        }
		
		if (driver != null) {
			driver.quit();
		}
	}

	// abstract methods
	protected abstract void beforeTestActions();

	protected abstract void afterTestActions();
}
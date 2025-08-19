package com.app.generic;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.app.util.TestData;


public abstract class BaseTest {
    public static WebDriver driver;   

    @BeforeMethod
    public void setUp() throws Exception {
        String browser = TestData.getProperty("browser");
        String url = TestData.getProperty("url");

        if(browser.equalsIgnoreCase("chrome")) {
        	System.setProperty("webdriver.chrome.driver",
        			   System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver");
        			driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")) {
     			driver = new FirefoxDriver();
     }
        
    
        // aur browsers ke liye bhi add kar sakte ho

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
    
    // abstract methods 
    protected abstract void beforeTestActions();
    protected abstract void afterTestActions();

}

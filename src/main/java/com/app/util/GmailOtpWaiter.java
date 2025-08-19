package com.app.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailOtpWaiter {

//    public static void main(String[] args) throws InterruptedException {
	public static void gmailClass() {
        // ⚠️ Replace with your Gmail + App Password (not your real password if 2FA is enabled)
        String email = "email-Id";
        String password = "password";

        // Setup ChromeDriver
//        System.setProperty("webdriver.chrome.driver",
// 			   System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver");
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://mail.google.com/");

            // Login
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("identifierId"))).sendKeys(email + Keys.RETURN);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Passwd"))).sendKeys(password + Keys.RETURN);

            // Wait for inbox
            Thread.sleep(10000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));

            // Search for OTP emails
            WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
            searchBox.clear();
            searchBox.sendKeys("testing" + Keys.RETURN);

            // --- Polling loop: wait until an OTP email arrives ---
            WebElement latestEmail = null;
            int retries = 0;
            while (retries < 30) {  // check up to ~3 minutes
                Thread.sleep(10000); // wait 6 sec before refreshing
                

                List<WebElement> emails = driver.findElements(By.cssSelector("table.F"));
                latestEmail = emails.get(0);
                System.out.println("latestEmail:"+latestEmail);
//                if (!emails.isEmpty()) {
//                    latestEmail = emails.get(0); // newest email at top
//                    break;
//                }
//                driver.navigate().refresh();
 //               retries++;
                System.out.println("Waiting for OTP email... attempt " + retries);
            }

            if (latestEmail == null) {
                System.out.println("No OTP email received within timeout.");
                return;
            }

            // Open the newest OTP email
            latestEmail.click();

            // Extract OTP
            WebElement msgBody = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.a3s.aiL")));
            String bodyText = msgBody.getText();

            Pattern pattern = Pattern.compile("\\b\\d{4,8}\\b");
            Matcher matcher = pattern.matcher(bodyText);

            if (matcher.find()) {
                System.out.println("✅ OTP Received: " + matcher.group());
            } else {
                System.out.println("No OTP found in the email body.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // driver.quit(); // Close browser if needed
        }
    }
}
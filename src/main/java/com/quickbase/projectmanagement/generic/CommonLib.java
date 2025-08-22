package com.quickbase.projectmanagement.generic;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/******************************************************************************
 * This is common Library, contains all the method common through out the
 * project *
 ******************************************************************************/

public class CommonLib extends BaseClass implements ICommonActions {
	WebDriver driver;

	@Override
	public void click(WebElement element) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			System.out.println("Clicked element: " + element);
		} catch (Exception e) {
			System.out.println("Failed to click element: " + element);
			throw e;
		}
	}

	@Override
	public void enterText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	@Override
	public void selectByVisibleText(WebElement element, String text) {
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}

	@Override
	protected void beforeTestActions() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void afterTestActions() {
		// TODO Auto-generated method stub
	}
}
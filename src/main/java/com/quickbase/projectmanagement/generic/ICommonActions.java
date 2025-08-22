package com.quickbase.projectmanagement.generic;

import org.openqa.selenium.WebElement;

/*
 * This is Interface to have common action to have uniformity across the framework
 */
public interface ICommonActions {

	void click(WebElement element);

	void enterText(WebElement element, String text);

	void selectByVisibleText(WebElement element, String text);

}

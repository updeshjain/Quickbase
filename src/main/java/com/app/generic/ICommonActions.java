package com.app.generic;

import org.openqa.selenium.WebElement;

public interface ICommonActions {
	
	void click(WebElement element);
    void enterText(WebElement element, String text);
    void selectByVisibleText(WebElement element, String text);

}

package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.generic.CommonLib;


public class TablesPage {
    WebDriver driver;
    CommonLib common = new CommonLib();

    
    @FindBy(xpath="//button[@id='newTableMenuButton']")
    private WebElement btnNewTable;
    
    @FindBy(xpath="//li[@id='createFromScratch']")
    private WebElement btnCreateFromScratch;

    @FindBy(xpath="//input[@id='react-select-2-input']")
    private WebElement tableNameField;

    @FindBy(xpath="//input[@data-test-id='SingleRecordInput']")
    private WebElement singleRecordField;
    
    @FindBy(xpath="//button[contains(@class,'dialogCancelButton')]")
    private WebElement btnCancel;
    
    @FindBy(xpath="//button[contains(@class,'dialogOkButton')]")
    private WebElement btnCreateTable;

    
    public TablesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void createNewTable(String tableName, String tableSingleRecord) {

    	common.click(btnNewTable);
    	common.click(btnCreateFromScratch);
    	common.enterText(tableNameField, tableName);
    	common.enterText(tableNameField, tableSingleRecord);
    	common.click(btnCreateTable);
    	common.click(btnCancel);
    }
    


}

package com.quickbase.projectmanagement.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.quickbase.projectmanagement.generic.CommonLib;
import com.quickbase.projectmanagement.util.Log;

/********************************************
 * This class contains all the methods and Webelements belongs to table page and
 * required to create new table and fields
 * 
 * @param <webElements>
 */

public class TablesPage<webElements> {
	WebDriver driver;
	private Logger logger = Log.getLogger(LoginPage.class);
	CommonLib common = new CommonLib();

	@FindBy(id = "newTableMenuButton")
	private WebElement btnNewTable;

	@FindBy(id = "btnNewTableAppTables")
	private WebElement btnCreateFromScratch;

	@FindBy(xpath = "//input[contains(@id,'react-select')]")
	private WebElement tableNameField;

	@FindBy(xpath = "//input[@data-test-id='SingleRecordInput']")
	private WebElement singleRecordField;

	@FindBy(xpath = "//button[contains(@class,'dialogCancelButton')]")
	private WebElement btnCancel;

	@FindBy(xpath = "//button[contains(@class,'dialogOkButton')]")
	private WebElement btnCreateTable;

	@FindBy(xpath = "//table[@id='appTablesListTable']//td//a[contains(@class,'AutoTip')]")
	private List<WebElement> listOfTablesInTableGrid;

	@FindBy(xpath = "//div[@data-test-id='newFieldDialog']")
	private WebElement newFieldDialog;

	@FindBy(xpath = "//input[contains(@id,'fieldName')]")
	private List<WebElement> fieldNameLabel;

	@FindBy(xpath = "//div[contains(@class,'fieldTypePicker__single-value')]")
	private List<WebElement> filedNameType;

	@FindBy(css = ".dialogOkButton")
	private WebElement addFieldsButton;
	
	@FindBy(id = "dialogDeleteTable")
	private WebElement dialogDeleteTable;
	
	@FindBy(id = "typeYesField")
	private WebElement typeYesField;
	
	@FindBy(xpath = "//button[text()='Delete Table']")
	private WebElement deleteTableButton;
	

	public TablesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*
	 * Method to create new table from Scratch
	 * 
	 * @param tableName : table Name to be created
	 * 
	 * @param tableSingleRecord : comments to be added while creating table
	 * 
	 * @param fields : it is array of strings, to added added on New fields to be
	 * added
	 * 
	 * @return numberOfFields : number of fields received from calling method,
	 * returning for asserting purpose
	 */
	public int createNewTable(String tableName, String tableSingleRecord, String... fields)
			throws InterruptedException {

		int numberOfFields = fields.length;

		logger.info("Clicking on new table button");
		common.click(btnNewTable);

		// waiting for 2 seconds to get stabilize New table creation menu options
		Thread.sleep(2000);

		logger.info("Clicking create from scratch menu option");
		common.click(btnCreateFromScratch);

		logger.info("Enter table name and comment on new table dialog");
		common.enterText(tableNameField, tableName);
		common.enterText(singleRecordField, tableSingleRecord);

		logger.info("Clicking on create button on new table dialog");
		common.click(btnCreateTable);

		logger.info("wait for field dialog to come up");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(newFieldDialog));

		logger.info("add lables");
		for (int i = 0; i < fields.length; i++) {
			common.enterText(fieldNameLabel.get(i), fields[i]);
		}

		logger.info("click on add fields button on add field dialog");
		common.click(addFieldsButton);

		return numberOfFields;
	}

	/*
	 * Get list of Tables from table Grid
	 * 
	 * @return tableList : List for tables available in table grid
	 */
	public List<String> getListofTablesFromGrid() {
		List<String> tableList = new ArrayList<String>();

		for (int i = 0; i < listOfTablesInTableGrid.size(); i++) {
			tableList.add(listOfTablesInTableGrid.get(i).getText());
		}

		return tableList;
	}

	/*
	 * Get number of fields associated with give table
	 * 
	 * @return No. of Fields
	 */
	public int getNumberOfFiledsForATable(String tableName) {

		String fieldValueXpath = String
				.format("//a[text()='%s' and contains(@class, 'AutoTip')]/parent::td/parent::tr/td[5]", tableName);
		return Integer.parseInt(driver.findElement(By.xpath(fieldValueXpath)).getText());
	}
	
	/*
	 * Delete give table from Grid
	 * 
	 * @param : tableName to be deleted
	 * 
	 */
	public void deleteTable(String tableName) {

		String deleteButtonXpath = String
				.format("//a[text()='%s' and contains(@class, 'AutoTip')]/parent::td/parent::tr//a[@class='RowAction Delete Icon']", tableName);
		common.click(driver.findElement(By.xpath(deleteButtonXpath)));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(dialogDeleteTable));
		common.enterText(typeYesField, "YES");
		common.click(deleteTableButton);
	}
}
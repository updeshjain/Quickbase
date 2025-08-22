package com.quickbase.projectmanagement.util;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataUtils {

	public static String getProperty(String key) throws Exception {
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}

	public static String readData(String sheetName, int row, int col) throws Exception {
		FileInputStream fis = new FileInputStream("src/main/resources/TestData.xlsx");
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sh = wb.getSheet(sheetName);
		String data = sh.getRow(row).getCell(col).toString();
		wb.close();
		return data;
	}

	public static void writeData(String sheetName, int row, int col, String value) throws Exception {
		FileInputStream fis = new FileInputStream("src/main/resources/TestData.xlsx");
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sh = wb.getSheet(sheetName);
		sh.getRow(row).createCell(col).setCellValue(value);
		FileOutputStream fos = new FileOutputStream("src/main/resources/TestData.xlsx");
		wb.write(fos);
		wb.close();
	}

}

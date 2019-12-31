package com.thrive.modules;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtils implements auto_constant{
	static FileInputStream fi;
	static XSSFWorkbook w;
	static XSSFSheet sheet;

	public static String getData(String sheetName,int row, int cell) {
		try {
			fi = new FileInputStream(new File(excelPath));
			w = new XSSFWorkbook(fi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sheet = w.getSheet(sheetName);
		String data = sheet.getRow(row).getCell(cell).getStringCellValue();
		return data;
	}
		
}

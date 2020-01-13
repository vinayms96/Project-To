package com.thrive.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtils {
	static FileInputStream fi;
	static XSSFWorkbook w;
	static XSSFSheet sheet;
	private static ArrayList<String> al;

	/*
	 * Getting the file path and accessing the workbook
	 */
	public static ArrayList<String> getData(String featureName) {
		try {
			fi = new FileInputStream(new File(Property.getProperty("excelPath")));
			w = new XSSFWorkbook(fi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Returning the ArrayList back to Calling Method
		return findRowData(featureName);
	}

	/*
	 * Iterate through the Rows and columns to get the Req data
	 */
	public static ArrayList<String> findRowData(String featureName) {
		int cellNum = 0, incre = 0;
		al = new ArrayList<>();

		// Getting the Sheet count
		int sheetCount = w.getNumberOfSheets();

		// Finding the "Column Number" of Feature header searching through the First Row
		for (int shRow = 0; shRow < sheetCount; shRow++) {
			if (w.getSheetName(shRow).equalsIgnoreCase("userData")) {
				sheet = w.getSheetAt(shRow);
				Iterator<Row> headRowIter = sheet.iterator();
				Iterator<Cell> headCellIter = headRowIter.next().cellIterator();
				while (headCellIter.hasNext()) {
					if (headCellIter.next().getStringCellValue().equalsIgnoreCase("feature")) {
						cellNum = incre;
						break;
					}
					incre++;
				}

				// Finding the exact cell of the required Value using the above column number
				while (headRowIter.hasNext()) {
					Row row = headRowIter.next();
					if (row.getCell(cellNum).getStringCellValue().equalsIgnoreCase(featureName)) {
						Iterator<Cell> cellIter = row.cellIterator();

						// Iterate through entire row and Add the values to ArrayList
						while (cellIter.hasNext()) {
							Cell cell = cellIter.next();
							if (cell.getCellType() == org.apache.poi.ss.usermodel.CellType.STRING) {
								al.add(cell.getStringCellValue());
							} else {
								al.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		return al;
	}

}

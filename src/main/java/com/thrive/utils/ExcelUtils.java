package com.thrive.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ExcelUtils {
    static FileInputStream fi;
    static XSSFWorkbook w;
    static XSSFSheet sheet;
    private static HashMap<String, String> map;
    private static ArrayList<String> al;

    /*
     * Get all the Header Values and Add it to ArrayList<String>
     */
    public static void get_keys() {
        al = new ArrayList<>();

        try {
            fi = new FileInputStream(new File(Property.getProperty("excelPath")));
            w = new XSSFWorkbook(fi);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Getting the Sheet count
        int sheetCount = w.getNumberOfSheets();

        // Finding the "Column Number" of Feature header searching through the First Row
        for (int shRow = 0; shRow < sheetCount; shRow++) {
            if (w.getSheetName(shRow).equalsIgnoreCase("userData")) {
                sheet = w.getSheetAt(shRow);
                // Shifts the cursor to first row
                Iterator<Row> headRowIter = sheet.iterator();
                // Starts shifting column wise
                Iterator<Cell> headCellIter = headRowIter.next().cellIterator();
                // Gets all the Column Heading Values
                while (headCellIter.hasNext()) {
                    al.add(headCellIter.next().getStringCellValue());
                }
            }
        }
    }

    /*
     * Iterate through the Rows and columns to get the Req data
     * Using only Iterator method
     */
    public static HashMap<String, String> findRowData(String featureName) {
        int cellNum = 0, incre = 0, count = 0;
        map = new HashMap<String, String>();

        // Getting the Sheet count
        int sheetCount = w.getNumberOfSheets();

        // Finding the "Column Number" of Feature header searching through the First Row
        for (int shRow = 0; shRow < sheetCount; shRow++) {
            if (w.getSheetName(shRow).equalsIgnoreCase("userData")) {
                sheet = w.getSheetAt(shRow);
                // Shifts the cursor to first row
                Iterator<Row> headRowIter = sheet.iterator();
                // Starts shifting column wise
                Iterator<Cell> headCellIter = headRowIter.next().cellIterator();
                // Iterate through the first row (Check all Columns for value)
                while (headCellIter.hasNext()) {
                    if (headCellIter.next().getStringCellValue().equalsIgnoreCase(featureName)) {
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
//                        for (int count = 0; count < al.size(); count++) {
                        while (cellIter.hasNext()) {
                            Cell cell = cellIter.next();
                            if (cell.getCellType() == org.apache.poi.ss.usermodel.CellType.STRING) {
                                map.put(al.get(count), cell.getStringCellValue());
                            } else {
                                map.put(al.get(count), NumberToTextConverter.toText(cell.getNumericCellValue()));
                            }
                            count++;
                        }
                    }
                }
            }
        }
        return map;
    }

    /*
     * Iterate through the Rows and columns to get the Req data
     * Using Both Iterator and For loop method
     * Returns HashMap<String, String>
     */
    public static HashMap<String, String> getData(String featureName) {

        int cellNum = 0, incre = 0;
        XSSFRow row = null;
        map = new HashMap<String, String>();

        // Getting the Sheet count
        int sheetCount = w.getNumberOfSheets();

        for (int shRow = 0; shRow < sheetCount; shRow++) {
            if (w.getSheetName(shRow).equalsIgnoreCase("userData")) {
                sheet = w.getSheetAt(shRow);
                // Shifts the cursor to first row
                Iterator<Row> headRowIter = sheet.iterator();
                // Starts shifting column wise
                Iterator<Cell> headCellIter = headRowIter.next().cellIterator();
                // Iterate through the first row (Check all Columns for value)
                while (headCellIter.hasNext()) {
                    if (headCellIter.next().getStringCellValue().equalsIgnoreCase(featureName)) {
                        cellNum = incre;
                        break;
                    }
                    incre++;
                }

                // Loop through the featureName column to find the Feature and get Row
                for (int r = 0; r < getRowCount(); r++) {
                    if (sheet.getRow(r).getCell(cellNum).getStringCellValue().equalsIgnoreCase(featureName)) {
                        row = sheet.getRow(r);
                    }
                }

                // Iterating through that row and getting the Cell values and mapping them to Header
                for (int c = 0; c < getCellCount(); c++) {
                    try {
                        if (row.getCell(c).getCellType() == CellType.STRING) {
                            map.put(al.get(c), row.getCell(c).getStringCellValue());
                        } else if (row.getCell(c).getCellType() == CellType.NUMERIC) {
                            map.put(al.get(c), NumberToTextConverter.toText(row.getCell(c).getNumericCellValue()));
                        }
                    } catch (Exception e) {
                        map.put(al.get(c), "Empty");
                    }
                }
            }
        }
        return map;
    }

    /*
    	Returns the Row Count
     */
    public static int getRowCount() {
        try {
            fi = new FileInputStream(new File(Property.getProperty("excelPath")));
            w = new XSSFWorkbook(fi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sheet = w.getSheetAt(0);
        return sheet.getLastRowNum() + 1;
    }

    /*
    	Returns the Cell Count
     */
    public static int getCellCount() {
        try {
            fi = new FileInputStream(new File(Property.getProperty("excelPath")));
            w = new XSSFWorkbook(fi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sheet = w.getSheetAt(0);
        Row row = sheet.getRow(0);
        return row.getLastCellNum();
    }

    public static ArrayList<String> getHead() {
        return al;
    }

}

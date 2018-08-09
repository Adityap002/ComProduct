package com.automationsnap.Util;

import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static Logger logger = LogManager.getLogger(ExcelUtility.class
			.getName());

	/*
	 * Set the File path, open Excel file
	 * 
	 * @params - Excel Path and Sheet Name
	 */
	public static void setExcelFile(String path, String sheetName)
			throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(path);

			// Access the excel data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			logger.debug("Workbook : " + path + "Sheet name" + sheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	public static String[][] getTestData(String tableName) {
		String[][] testData = null;

		try {
			// Handle numbers and strings
			DataFormatter formatter = new DataFormatter();
			XSSFCell[] boundaryCells = findCells(tableName);
			logger.debug("Test Name is " + tableName);
			XSSFCell startCell = boundaryCells[0];

			XSSFCell endCell = boundaryCells[1];

			int startRow = startCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex() - 1;
			int startCol = startCell.getColumnIndex() + 1;
			int endCol = endCell.getColumnIndex() - 1;

			logger.debug("Start row " + startRow + "End Row " + endRow
					+ "Start Col " + startCol + "End col " + endCol);

			testData = new String[endRow - startRow + 1][endCol - startCol + 1];

			for (int i = startRow; i < endRow + 1; i++) {
				for (int j = startCol; j < endCol + 1; j++) {
					// testData[i-startRow][j-startCol] =
					// ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
					Cell cell = ExcelWSheet.getRow(i).getCell(j);
					testData[i - startRow][j - startCol] = formatter
							.formatCellValue(cell);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}

	public static XSSFCell[] findCells(String tableName) {
		DataFormatter formatter = new DataFormatter();
		String pos = "begin";
		XSSFCell[] cells = new XSSFCell[2];

		for (Row row : ExcelWSheet) {
			for (Cell cell : row) {
				// if (tableName.equals(cell.getStringCellValue())) {
				if (tableName.equals(formatter.formatCellValue(cell))) {
					if (pos.equalsIgnoreCase("begin")) {
						cells[0] = (XSSFCell) cell;
						logger.debug("text begin");
						pos = "end";
					} else {
						cells[1] = (XSSFCell) cell;
						logger.debug("text end");
					}
				}
			}
		}
		return cells;
	}
}
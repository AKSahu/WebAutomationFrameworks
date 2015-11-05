package coreaf.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class allows us the use of an Excel spreadsheet to provide input data to
 * a test or set of tests.
 * 
 * Note: This class needs following jars:
 * 
 * <pre>
 * poi-3.13.jar,<br> poi-ooxml-3.13.jar,<br> poi-ooxml-schemas-3.13.jar,<br> xmlbeans-2.6.0.jar<br> and dom4j-1.6.1.jar
 * </pre>
 * 
 * Example usage:
 * 
 * <code><br>
 * DataDrivenExcel obj = new DataDrivenExcel("D:/workspace/DataDrivenXSL/data/SampleExcel.xlsx", "Sheet 1");<br>
 * obj.getCell(1, 3);<br>
 * </code>
 * 
 * @author A. K. Sahu
 * 
 */

public class DataDrivenExcel {

	private static Logger log = Logger.getLogger(DataDrivenExcel.class);

	private Workbook wb;
	private Sheet ws;

	/**
	 * Opens a excel sheet
	 * 
	 * @param fileName
	 *            name of the file where you want data
	 * @param sheetName
	 *            name of the sheet in the excel file
	 */
	public DataDrivenExcel(String fileName, String sheetName) {
		try {
			if (fileName.indexOf("xlsx") < 0) {
				wb = new HSSFWorkbook(new FileInputStream(new File(fileName)));
				ws = wb.getSheet(sheetName);
			} else {
				wb = new XSSFWorkbook(fileName);
				ws = (XSSFSheet) wb.getSheet(sheetName);
			}
		} catch (IOException io) {
			log.error("Invalid file '" + fileName + "' or incorrect sheet '" + sheetName + "', enter a valid one");
			log.error(io.getMessage());
		}
	}

	/**
	 * Gets a cell value from the opened sheet
	 * 
	 * @param rowIndex
	 *            starting with 0 index
	 * @param columnIndex
	 *            starting with 0 index
	 * @return
	 */
	public String getCell(int rowIndex, int columnIndex) {
		Cell cell = null;
		try {
			cell = ws.getRow(rowIndex).getCell(columnIndex);
		} catch (Exception e) {
			log.error(
					"The cell with row '" + rowIndex + "' and column '" + columnIndex + "' doesn't exist in the sheet");
			log.error(e.getMessage());
		}
		return new DataFormatter().formatCellValue(cell);
	}

	/**
	 * Gets a row values from the opened sheet
	 * 
	 * @param rowIndex
	 *            starting with 0 index
	 * @return
	 */
	public List<Object> getRow(int rowIndex) {
		List<Object> rowData = new ArrayList<Object>();

		try {
			Row row = ws.getRow(rowIndex);
			Iterator<Cell> cells = row.cellIterator();

			while (cells.hasNext()) {
				Cell cell = cells.next();
				rowData.add(new DataFormatter().formatCellValue(cell));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return rowData;
	}

	/**
	 * Gets first row number
	 * 
	 * @return
	 */
	public int getFirstRowNum() {
		return ws.getFirstRowNum();
	}

	/**
	 * Gets last row number
	 * 
	 * @return
	 */
	public int getLastRowNum() {
		return ws.getLastRowNum();
	}
}
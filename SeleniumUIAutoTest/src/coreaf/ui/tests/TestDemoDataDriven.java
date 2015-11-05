package coreaf.ui.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import coreaf.framework.base.BasePage;
import coreaf.framework.util.DataDrivenExcel;
import coreaf.framework.util.TestEnvironment;
import coreaf.ui.base.TestBase;

/**
 * A test class to use parallel execution with multiple set of data
 * 
 * @author A. K. Sahu
 *
 */
public class TestDemoDataDriven extends TestBase {

	DataDrivenExcel userData = new DataDrivenExcel(
			TestEnvironment.getDataDirectory() + File.separator + "TestData.xlsx", "EMPLOYEE_DATA");

	@DataProvider(name = "cellData")
	public Object[][] getDataCells() throws Exception {

		List<Object> dataList = new ArrayList<Object>();

		int startRow = userData.getFirstRowNum() + 1;// excluding header row
		int endRow = userData.getLastRowNum();
		int totalRow = userData.getLastRowNum() - userData.getFirstRowNum();
		System.out.println("Start row: " + startRow + " :: End row: " + endRow + " :: Total rows: " + totalRow);
		while (startRow < totalRow) {

			Object[] dataLine = new Object[totalRow];// 5 parameters in test
			for (int column = 0; column < dataLine.length; column++) {
				dataLine[column] = userData.getCell(startRow, column);
			}

			dataList.add(dataLine);

			startRow++;
		}

		Object[][] data = new Object[dataList.size()][];
		for (int i = 0; i < dataList.size(); i++)
			data[i] = (Object[]) dataList.get(i);

		return data;
	}

	@DataProvider(name = "rowsOfData")
	public Object[][] getDataRows() throws Exception {

		List<Object> dataList = new ArrayList<Object>();

		int startRow = userData.getFirstRowNum() + 1;// excluding header row
		int endRow = userData.getLastRowNum();
		int totalRow = userData.getLastRowNum() - userData.getFirstRowNum();
		System.out.println("Start row: " + startRow + " :: End row: " + endRow + " :: Total rows: " + totalRow);
		while (startRow < totalRow) {

			Object[] dataLine = userData.getRow(startRow).toArray();
			dataList.add(dataLine);

			startRow++;
		}

		Object[][] data = new Object[dataList.size()][];
		for (int i = 0; i < dataList.size(); i++)
			data[i] = (Object[]) dataList.get(i);

		return data;
	}

	@Test(dataProvider = "cellData", description = "Reading data by cells")
	public void testReadExcelCell(String empID, String empName, String email, String dateOfJoin, String aBoleanValue) {

		System.out.println(empID + "::" + empName + "::" + email + "::" + dateOfJoin + "::" + aBoleanValue);

		BasePage.get("http://www.google.com");
		BasePage.findElement(By.name("q")).sendKeys(empID + "::" + empName + "::" + email + "::" + dateOfJoin);
	}

	@Test(dataProvider = "rowsOfData", description = "Reading data by rows")
	public void testReadExcelRow(String empID, String empName, String email, String dateOfJoin, String aBoleanValue) {

		System.out.println(empID + "::" + empName + "::" + email + "::" + dateOfJoin + "::" + aBoleanValue);

		BasePage.get("http://www.google.com");
		BasePage.findElement(By.name("q")).sendKeys(empID + "::" + empName + "::" + email + "::" + dateOfJoin);
	}
}

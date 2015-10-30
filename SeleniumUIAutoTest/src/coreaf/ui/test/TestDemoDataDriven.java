package coreaf.ui.test;

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

	@DataProvider(name = "loginToAppWithAllRoles")
	public Object[][] getLoginDataForAllRoles() throws Exception {

		DataDrivenExcel userData = new DataDrivenExcel(
				TestEnvironment.getDataDirectory() + File.separator + "TestData.xlsx", "EMPLOYEE_DATA");

		List<Object> dataList = new ArrayList<Object>();

		int i = 1;// excluding header row
		int totalRows = 6;
		while (i < totalRows) {
			System.out.println("loginToAppWithAllRoles : line : " + i);

			Object[] dataLine = new Object[5];
			dataLine[0] = userData.getCell(i, 0);
			dataLine[1] = userData.getCell(i, 1);
			dataLine[2] = userData.getCell(i, 2);
			dataLine[3] = userData.getCell(i, 3);
			dataLine[4] = userData.getCell(i, 4);

			dataList.add(dataLine);

			i++;
		}

		Object[][] data = new Object[dataList.size()][];
		for (i = 0; i < dataList.size(); i++)
			data[i] = (Object[]) dataList.get(i);

		return data;
	}

	@Test(dataProvider = "loginToAppWithAllRoles", description = "Login with different roles")
	public void testLogin(String empID, String empName, String email, String dateOfJoin, String aBoleanValue) {

		System.out.println(empID + "::" + empName + "::" + email + "::" + dateOfJoin + "::" + aBoleanValue);

		BasePage.get("http://www.google.com");
		BasePage.findElement(By.name("q")).sendKeys(empID + "::" + empName + "::" + email + "::" + dateOfJoin);
	}
}

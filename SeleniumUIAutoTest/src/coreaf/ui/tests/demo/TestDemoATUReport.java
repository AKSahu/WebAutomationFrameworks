package coreaf.ui.tests.demo;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import coreaf.framework.base.BasePage;
import coreaf.ui.base.TestBase;

/**
 * A test class to use ATU reporter
 * 
 * @author A. K. Sahu
 *
 */
@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class TestDemoATUReport extends TestBase {

	@Test
	public void testMethod1() {
		SoftAssert sAssert = new SoftAssert();
		ATUReports.setAuthorInfo("Aswini Kumar Sahu", Utils.getCurrentTime(), "1.0");
		Reporter.log(LOG_FILE_INFO);

		BasePage.get("http://www.google.com");
		Reporter.log("Application launched successfully!");
		ATUReports.add("Info message", LogAs.INFO, null);

		String title = BasePage.getTitle();
		sAssert.assertEquals(title, "Google ");
		ATUReports.add("Verifying title", title, "Google ", (title.equals("Google")) ? LogAs.PASSED : LogAs.FAILED,
				new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		BasePage.findElement(By.name("q")).sendKeys("aksahu blog");
		title = BasePage.getTitle();
		ATUReports.add("Verifying title", title, "Google Search",
				(title.equals("Google Search")) ? LogAs.PASSED : LogAs.FAILED,
				new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		sAssert.assertEquals(title, "Google Search");

		Reporter.log("Test execution completed!");
		sAssert.assertAll();
	}

}

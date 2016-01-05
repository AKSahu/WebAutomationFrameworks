package coreaf.ui.base;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import atu.testng.reports.ATUReports;
import coreaf.framework.base.BasePage;
import coreaf.framework.base.DriverFactory;
import coreaf.framework.base.DriverManager;
import coreaf.framework.util.ConfigUtil;
import coreaf.framework.util.ScreenshotCapture;
import coreaf.framework.util.TestEnvironment;

/**
 * This is the base class for all test classes in the project. Every test class
 * should extend this class.
 * 
 * @author A. K. Sahu
 *
 */
public class TestBase {

	private static Logger log = Logger.getLogger(TestBase.class);
	String browser = null;
	protected String LOG_FILE_INFO = "<font color='maroon'> You can find the complete logs at " + "<a href=\""
			+ TestEnvironment.getLogsDirectory()
			+ "/automationLog.log\" target=\"_blank\" >automationLog.log</a></font>";

	{
		System.setProperty("atu.reporter.config", "config" + File.separator + "atu.properties");
	}

	@Parameters({ "browserName" })
	@BeforeClass
	public void setUp(@Optional("no-browser") String browserName) {
		this.browser = browserName;
		if (browser.equals("no-browser")) {
			browser = ConfigUtil.getBrowser();
		}
		log.info("Browser name found for test execution is:" + browser);
		Reporter.log("Browser name found for test execution is:" + browser);
		// WebDriver driver = DriverFactory.createInstance(browser);
		// DriverManager.setWebDriver(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		// if (DriverManager.getDriver() == null) {
		WebDriver driver = DriverFactory.createInstance(browser);
		DriverManager.setWebDriver(driver);
		ATUReports.setWebDriver(driver);
		ATUReports.indexPageDescription = "Testing AKSahu's Wiki Page application <br/> "
				+ "<font color='blue'>http://knowledgebase-wiki.appspot.com/</font> " + "and <b>A.K.Sahu's Blog</b>";

		log.info("WebDriver object was nullified and hence reinitiated it.");
		// }
		BasePage.maximize();
		// we can login to the application here if required
	}

	@AfterMethod
	public void afterMethod(ITestResult testResult) {
		ScreenshotCapture.takeScreenshot(DriverManager.getDriver(),
				testResult.getTestClass().getName() + "." + testResult.getName());

		// we can logout from the application here if required
		// BasePage.deleteAllCookies();
		if (DriverManager.getDriver() != null) {
			BasePage.quit();
		}
	}

	@AfterClass
	public void tearDown() {
		// if (DriverManager.getDriver() != null) {
		// BasePage.quit();
		// }
	}

}

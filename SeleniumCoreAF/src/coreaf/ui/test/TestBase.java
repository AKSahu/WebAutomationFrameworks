package coreaf.ui.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import coreaf.framework.base.BasePage;
import coreaf.framework.base.DriverFactory;
import coreaf.framework.util.ConfigUtil;
import coreaf.framework.util.ScreenshotCapture;

public class TestBase {

	private static Logger log = Logger.getLogger(TestBase.class);
	protected BasePage basePage = new BasePage();
	String browser = null;

	@Parameters({ "browserName" })
	@BeforeClass
	public void setUp(@Optional("no-browser") String browserName) {
		this.browser = browserName;
		if (browser.equals("no-browser")) {
			browser = ConfigUtil.getBrowser();
		}
		log.info("Browser name found for test execution is:" + browser);
		WebDriver driver = DriverFactory.createInstance(browser);
		BasePage.setWebDriver(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		if (BasePage.getDriver() == null) {
			WebDriver driver = DriverFactory.createInstance(browser);
			BasePage.setWebDriver(driver);
			log.info("WebDriver object was nullified and hence reinitiated it.");
		}
		basePage.maximizeWindow();
		// we can login to the application here if required
	}

	@AfterMethod
	public void afterMethod(ITestResult testResult) {
		ScreenshotCapture.takeScreenshot(BasePage.getDriver(),
				testResult.getTestClass().getName() + "." + testResult.getName());

		// we can logout from the application here if required
		BasePage.getDriver().manage().deleteAllCookies();
		log.info("All browser cookies has been deleted.");
	}

	@AfterClass
	public void tearDown() {
		if (BasePage.getDriver() != null) {
			BasePage.getDriver().quit();
		}
	}

}

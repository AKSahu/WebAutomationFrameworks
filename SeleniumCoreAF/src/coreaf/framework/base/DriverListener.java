package coreaf.framework.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import coreaf.framework.util.ConfigUtil;
import coreaf.framework.util.ScreenshotCapture;

/**
 * This class initializes the driver object using driver factory before each
 * test method invocation and quits the browser after each method invocation
 * 
 * @author A. K. Sahu
 *
 */
public class DriverListener implements IInvokedMethodListener {

	private static Logger log = Logger.getLogger(DriverListener.class);

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
			if (browserName == null) {
				browserName = ConfigUtil.getBrowser();
			}
			log.info("Browser name found for test execution is:" + browserName);
			WebDriver driver = DriverFactory.createInstance(browserName);
			DriverManager.setWebDriver(driver);
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			WebDriver driver = DriverManager.getDriver();
			if (driver != null) {
				ScreenshotCapture.takeScreenshot(driver,
						testResult.getTestClass().getName() + "." + testResult.getName());
				driver.quit();
			}
		}
	}
}
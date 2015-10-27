package coreaf.framework.report;

import java.io.File;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import coreaf.framework.util.TestEnvironment;

/**
 * This class has to be added to the testng task to listen for events.
 * 
 * It has an extra functionality that it takes a screenshot(of the browser
 * window) when a test pass or fails or skip.
 * 
 * @author A. K. Sahu
 */
public class ReportNGListener extends TestListenerAdapter {

	private static Logger log = Logger.getLogger(ReportNGListener.class);

	@Override
	public void onTestStart(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		System.setProperty("org.uncommons.reportng.frames", "false");
		System.setProperty("org.uncommons.reportng.title", "Automated Test Execution Report");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		doReportNGReporting(result, "FAILED");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		doReportNGReporting(result, "SKIPPED");
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		doReportNGReporting(result, "PASSED");
	}

	/**
	 * The method does all reporting into ReportsNG.
	 * 
	 * @param result
	 * @param status
	 */
	private void doReportNGReporting(ITestResult result, String status) {

		try {

			String testClass = result.getTestClass().getName();
			String testName = result.getName();
			String screenshotFileUrl = "file:///" + TestEnvironment.getScreenshotDirectory() + File.separator
					+ testClass + "." + testName + ".png";

			Reporter.setCurrentTestResult(result);

			Object[] parameters = result.getParameters();
			Reporter.log("<p><font face=arial size=2 color=000099");
			if (parameters.length > 0)
				Reporter.log("<p>Total number of input parameters: " + parameters.length + "<p>");

			for (int i = 0; i < parameters.length; i++) {
				Reporter.log("Parameter: " + parameters[i]);
			}

			Reporter.log("<b>Screenshot</b><br>");
			Reporter.log("<p><a href='" + screenshotFileUrl + "' target='_blank'>" + "<img src='" + screenshotFileUrl
					+ "' height='100' width='100' title='" + testName + "'/></a>");
			Reporter.log("<p>");
			Reporter.log("<font size=1>Click thumbnail image to view screenshot</font><p><br></font>");

			Reporter.setCurrentTestResult(null);

		} catch (Exception e) {
			log.error("Couldn't prepare reportng report." + e.getMessage());
		}
	}

}

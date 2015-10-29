package coreaf.ui.test;

import org.testng.annotations.Test;

import coreaf.framework.base.BasePage;
import coreaf.framework.util.TestEnvironment;

/**
 * A test class to use parallel execution
 * 
 * @author A. K. Sahu
 *
 */
public class TestDemoSafari extends TestBase {

	@Test
	public void testMethod1() {
		BasePage.get("http://www.microsoft.com/");
		String browser = TestEnvironment.getCurrentBrowserName(BasePage.getDriver());

		System.out.println("Browser:" + browser);
	}

	@Test
	public void testMethod2() {
		BasePage.get("http://www.apple.com/");
		String browser = TestEnvironment.getCurrentBrowserName(BasePage.getDriver());

		System.out.println("Browser:" + browser);

	}
}

package coreaf.ui.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import coreaf.framework.base.DriverManager;
import coreaf.framework.util.TestEnvironment;

/**
 * A test class to use parallel execution
 * 
 * @author A. K. Sahu
 *
 */
public class TestDemoFirefox {

	@Test
	public void testMethod1() {
		invokeBrowser("http://www.ndtv.com");
	}

	@Test
	public void testMethod2() {
		invokeBrowser("http://www.facebook.com");

	}

	private void invokeBrowser(String url) {
		System.out.println("Thread id = " + Thread.currentThread().getId());

		WebDriver driver = DriverManager.getDriver();
		driver.get(url);
		String browser = TestEnvironment.getCurrentBrowserName(driver);

		System.out.println("Browser:" + browser);
	}
}

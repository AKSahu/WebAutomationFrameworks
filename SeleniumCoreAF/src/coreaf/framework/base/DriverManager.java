package coreaf.framework.base;

import org.openqa.selenium.WebDriver;

/**
 * This class models the getter and setter for web driver instance
 * 
 * @author A. K. Sahu
 *
 */
public class DriverManager {

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	/**
	 * Gives an instance of webdriver from the thread
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return webDriver.get();
	}

	/**
	 * Sets the instance of webdriver to the thread
	 * 
	 * @param driver
	 */
	static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}

}

package coreaf.framework.base;

import org.openqa.selenium.WebDriver;

/**
 * This class manages the driver instance
 * 
 * @author A. K. Sahu
 *
 */
public class DriverManager {

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	/**
	 * @see {@link java.lang.ThreadLocal#get()}
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {

		return webDriver.get();
	}

	/**
	 * @see {@link java.lang.ThreadLocal#set(WebDriver value)}
	 * 
	 * @param driver
	 */
	public static void setWebDriver(WebDriver driver) {

		webDriver.set(driver);
	}
}

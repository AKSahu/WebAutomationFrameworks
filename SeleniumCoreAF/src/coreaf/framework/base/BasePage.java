package coreaf.framework.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * This page models all the selenium webdriver functionalities and few
 * customized optimal keywords
 * 
 * @author A. K. Sahu
 *
 */
public class BasePage {

	private static Logger log = Logger.getLogger(BasePage.class);
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

	/**
	 * @see {@link org.openqa.selenium.WebDriver#get(String)}
	 */
	public static void get(String url) {
		getDriver().get(url);
		log.info("Opened URL :: " + url);
	}

	/**
	 * @see {@link org.openqa.selenium.WebDriver.Window#maximize()}
	 */
	public static void maximizeWindow() {
		getDriver().manage().window().maximize();
		log.info("Browser window has been maximized.");
	}

	// will add more required methods here
}

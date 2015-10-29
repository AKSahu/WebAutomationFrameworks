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

	public void maximizeWindow() {
		getDriver().manage().window().maximize();
		log.info("Browser window has been maximized.");
	}

	// will add more required methods here
}

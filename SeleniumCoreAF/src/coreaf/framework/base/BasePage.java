package coreaf.framework.base;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;

import coreaf.framework.util.TestEnvironment;

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
	 * @see org.openqa.selenium.WebDriver#getCurrentUrl()
	 */
	public static String getCurrentUrl() {

		return BasePage.getDriver().getCurrentUrl();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#getTitle()
	 */
	public static String getTitle() {

		return BasePage.getDriver().getTitle();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#findElements(org.openqa.selenium.By)
	 */
	public static List<WebElement> findElements(By by) {

		return BasePage.getDriver().findElements(by);
	}

	/**
	 * @see org.openqa.selenium.WebDriver#findElement(org.openqa.selenium.By)
	 */
	public static WebElement findElement(By by) {

		return BasePage.getDriver().findElement(by);
	}

	/**
	 * @see org.openqa.selenium.WebDriver#getPageSource()
	 */
	public static String getPageSource() {

		return BasePage.getDriver().getPageSource();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#close()
	 */
	public static void close() {

		BasePage.getDriver().close();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#quit()
	 */
	public static void quit() {

		BasePage.getDriver().quit();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#getWindowHandles()
	 */
	public static Set<String> getWindowHandles() {

		return BasePage.getDriver().getWindowHandles();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#getWindowHandle()
	 */
	public static String getWindowHandle() {

		return BasePage.getDriver().getWindowHandle();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#switchTo()
	 */
	public static TargetLocator switchTo() {

		return BasePage.getDriver().switchTo();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#navigate()
	 */
	public static Navigation navigate() {

		return BasePage.getDriver().navigate();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#manage()
	 */
	public static Options manage() {

		return BasePage.getDriver().manage();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#get(java.lang.String)
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

		if (TestEnvironment.getCurrentBrowserName(BasePage.getDriver()).equals(DriverFactory.CHROME)) {

			Point targetPosition = new Point(0, 0);
			BasePage.getDriver().manage().window().setPosition(targetPosition);

			String w = "return screen.availWidth";
			String h = "return screen.availHeight";
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) BasePage.getDriver();
			int width = ((Long) javascriptExecutor.executeScript(w)).intValue();
			int height = ((Long) javascriptExecutor.executeScript(h)).intValue();
			Dimension targetSize = new Dimension(width, height);

			BasePage.getDriver().manage().window().setSize(targetSize);
		}

		log.info("Browser window has been maximized.");
	}

	/**
	 * @see {@link org.openqa.selenium.WebDriver.Navigation#refresh()}
	 */
	public static void refresh() {

		getDriver().navigate().refresh();
		log.info("The page has been refreshed.");
	}

	// will add more required methods here
}

package coreaf.framework.base;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
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
public class BasePage extends DriverManager {

	private static Logger log = Logger.getLogger(BasePage.class);

	/**
	 * @see org.openqa.selenium.WebDriver#getCurrentUrl()
	 */
	public static String getCurrentUrl() {

		return getDriver().getCurrentUrl();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#getTitle()
	 */
	public static String getTitle() {

		return getDriver().getTitle();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#findElements(org.openqa.selenium.By)
	 */
	public static List<WebElement> findElements(By by) {

		return getDriver().findElements(by);
	}

	/**
	 * @see org.openqa.selenium.WebDriver#findElement(org.openqa.selenium.By)
	 */
	public static WebElement findElement(By by) {

		return getDriver().findElement(by);
	}

	/**
	 * @see org.openqa.selenium.WebDriver#getPageSource()
	 */
	public static String getPageSource() {

		return getDriver().getPageSource();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#close()
	 */
	public static void close() {

		getDriver().close();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#quit()
	 */
	public static void quit() {

		getDriver().quit();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#getWindowHandles()
	 */
	public static Set<String> getWindowHandles() {

		return getDriver().getWindowHandles();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#getWindowHandle()
	 */
	public static String getWindowHandle() {

		return getDriver().getWindowHandle();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#switchTo()
	 */
	public static TargetLocator switchTo() {

		return getDriver().switchTo();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#navigate()
	 */
	public static Navigation navigate() {

		return getDriver().navigate();
	}

	/**
	 * @see org.openqa.selenium.WebDriver#manage()
	 */
	public static Options manage() {

		return getDriver().manage();
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

		if (TestEnvironment.getCurrentBrowserName(getDriver()).equals(DriverFactory.CHROME)) {

			Point targetPosition = new Point(0, 0);
			getDriver().manage().window().setPosition(targetPosition);

			String w = "return screen.availWidth";
			String h = "return screen.availHeight";
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
			int width = ((Long) javascriptExecutor.executeScript(w)).intValue();
			int height = ((Long) javascriptExecutor.executeScript(h)).intValue();
			Dimension targetSize = new Dimension(width, height);

			getDriver().manage().window().setSize(targetSize);
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

	/**
	 * @see {@link org.openqa.selenium.WebDriver.Options#deleteAllCookies()}
	 */
	public static void deleteAllCookies() {
		
		getDriver().manage().deleteAllCookies();
		log.info("All browser cookies has been deleted.");

	}
	
	// will add more required methods here
}

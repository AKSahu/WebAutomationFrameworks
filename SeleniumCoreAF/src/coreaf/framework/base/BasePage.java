package coreaf.framework.base;

import static coreaf.framework.base.DriverManager.getDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

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
	public static void maximize() {

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

	/**
	 * Finds an element by executing the given jQuery statement
	 * <code>jQueryScript</code>
	 * 
	 * @param jQueryScript
	 *            a JQuery Script e.g. For an element 'div' with class 'sorted'
	 *            <br>
	 *            (1)To find the element use:
	 *            <code>jQuery(\"div.sorted\")</code>. Here we need to use
	 *            jQuery selectors only like in this example 'div.sorted'. <br>
	 *            (2)To find parent of the element use:
	 *            <code>jQuery(\"div.sorted\").parent()</code> <br>
	 *            (3)To mouseover on the element use:
	 *            <code>jQuery(\"div.sorted\").mouseover()</code>
	 * @return WebElement that is described by that jQuery statement
	 *         <code>jQueryScript</code>, if not found, returns null.
	 */
	public WebElement findElementByJQuery(final String jQueryScript) {
		WebElement element = null;

		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(50, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		try {
			element = wait.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver d) {
					String script = "return " + jQueryScript + ".get(0);";
					JavascriptExecutor jse = (JavascriptExecutor) d;
					WebElement webElement = (WebElement) jse.executeScript(script);
					return webElement;
				}
			});
		} catch (Exception e) {
			log.error("Failed to find the element by executing JQuery script '" + jQueryScript + "'." + e);
		}
		return element;
	}

	/**
	 * Find all the elements by executing the given jQuery statement
	 * <code>jQueryScript</code>
	 * 
	 * @param jQueryScript
	 *            a JQuery Script e.g. For all the 'div' elements with class
	 *            'sorted' <br>
	 *            (1)To find the element use:
	 *            <code>jQuery(\"div.sorted\")</code>. Here we need to use
	 *            jQuery selectors only like in this example 'div.sorted'. <br>
	 *            (2)To find parent of the element use:
	 *            <code>jQuery(\"div.sorted\").parent()</code> <br>
	 *            (3)To mouseover on the element use:
	 *            <code>jQuery(\"div.sorted\").mouseover()</code>
	 * @return WebElement that is described by that jQuery statement
	 *         <code>jQueryScript</code>, if not found, returns null.
	 */
	public List<WebElement> findElementsByJQuery(final String jQueryScript) {
		List<WebElement> elements = null;

		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(50, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		try {
			elements = wait.until(new Function<WebDriver, List<WebElement>>() {
				@Override
				public List<WebElement> apply(WebDriver d) {
					List<WebElement> webElements = new ArrayList<WebElement>();
					for (int i = 0;; i++) {
						String script = "return " + jQueryScript + ".get(" + i + ");";
						JavascriptExecutor jse = (JavascriptExecutor) d;
						WebElement webElement = (WebElement) jse.executeScript(script);
						if (webElement != null) {
							webElements.add(webElement);
						} else {
							break;
						}
					}
					return webElements;
				}
			});
		} catch (Exception e) {
			log.error("Failed to find the elements by executing JQuery script '" + jQueryScript + "'." + e);
		}
		return elements;
	}

	/**
	 * Drags the web element <code>byFromElement</code> and drop at web element
	 * <code>byToElement</code>
	 * 
	 * @param byFromElement
	 * @param byToElement
	 */
	public void dragAndDrop(By byFromElement, By byToElement) {

		log.info("Drag and drop from the element " + byFromElement + " to the element " + byToElement);
		Actions builder = new Actions(getDriver());

		WebElement fromWebElement = getDriver().findElement(byFromElement);
		WebElement toWebElement = getDriver().findElement(byToElement);
		Action dragAndDrop = builder.clickAndHold(fromWebElement).moveToElement(toWebElement).release(toWebElement)
				.build();

		dragAndDrop.perform();
	}

	/**
	 * Waits for a page to load for <code>timeOutInSeconds</code> number of
	 * seconds.
	 * 
	 * @param timeOutInSeconds
	 */
	public void waitForPageToLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			log.info("Waiting for page to load...");
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			log.error("Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	/**
	 * Waits until there are no more active ajax connection and until the
	 * specified <code>timeoutInSeconds</code> is timeout
	 * 
	 * @param timeoutInSeconds
	 */
	public void waitForAjax(long timeoutInSeconds) {
		log.info("Checking active ajax calls by calling jquery.active");
		try {
			if (getDriver() instanceof JavascriptExecutor) {
				JavascriptExecutor jsDriver = (JavascriptExecutor) getDriver();

				for (int i = 0; i < timeoutInSeconds; i++) {
					Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
					// return should be a number
					if (numberOfAjaxConnections instanceof Long) {
						Long n = (Long) numberOfAjaxConnections;
						log.info("Number of active jquery ajax calls: " + n);
						if (n.longValue() == 0L)
							break;
					}
					Thread.sleep(1000);// 1 second sleep
				}
			} else {
				log.error("Web driver: " + getDriver() + " cannot execute javascript");
			}
		} catch (Exception e) {
			log.error("Failed to wait for ajax response: " + e);
		}
	}

	/**
	 * Waits until there are no more active ajax connection and until the
	 * specified <code>timeoutInSeconds</code> is timeout
	 * 
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForAjaxComplete(final long timeOutInSeconds) {
		log.info("Checking active ajax calls by calling jquery.active");

		final WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		return wait.until(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				try {
					final long startTime = System.currentTimeMillis();
					final JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

					while ((startTime + timeOutInSeconds) >= System.currentTimeMillis()) {
						final Boolean scriptResult = (Boolean) javascriptExecutor
								.executeScript("return jQuery.active == 0");
						if (scriptResult)
							return true;

						Thread.sleep(100);

					}
				} catch (InterruptedException e) {
					log.error("Failed to wait for ajax response: " + e);
				}
				return false;
			}
		});
	}

	// will add more required methods here
}

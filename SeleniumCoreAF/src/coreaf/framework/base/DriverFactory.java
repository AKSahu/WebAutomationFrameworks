package coreaf.framework.base;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import coreaf.framework.util.TestEnvironment;

/**
 * This class initializes the driver object for various browser drivers
 * 
 * @author A. K. Sahu
 *
 */
public class DriverFactory {

	private static Logger log = Logger.getLogger(DriverFactory.class);
	public static final String FIREFOX = "firefox";
	public static final String CHROME = "chrome";
	public static final String INTERNET_EXPLORER = "ie";
	public static final String SAFARI = "safari";

	public static WebDriver createInstance(String browserName) {

		WebDriver driver = null;

		if (browserName.equalsIgnoreCase(FIREFOX)) {
			log.info("initializing 'firefox' driver...");
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase(CHROME)) {
			if (TestEnvironment.isPlatformWindows()) {
				System.setProperty("webdriver.chrome.driver", TestEnvironment.getDriversDirectory() + File.separator
						+ "windows" + File.separator + "chromedriver.exe");
				log.info("'chrome' driver system property set for " + TestEnvironment.getCurrentOperatingSystem());
			} else if (TestEnvironment.isPlatformMac()) {
				System.setProperty("webdriver.chrome.driver",
						TestEnvironment.getDriversDirectory() + File.separator
						+ "mac" + File.separator + "chromedriver");
				log.info("'chrome' driver system property set for " + TestEnvironment.getCurrentOperatingSystem());
			} else {
				log.info("'chrome' driver system property couldn't set for "
						+ TestEnvironment.getCurrentOperatingSystem());
			}
			log.info("initializing 'chrome' driver...");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase(INTERNET_EXPLORER)) {
			Assert.assertTrue("Internet Explorer is not supporting in this OS", TestEnvironment.isPlatformWindows());

			DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			System.setProperty("webdriver.ie.driver",
					TestEnvironment.getDriversDirectory() + File.separator + "IEDriverServer.exe");

			log.info("initializing 'internet explorer' driver...");
			driver = new InternetExplorerDriver(dc);

		} else if (browserName.equalsIgnoreCase(SAFARI)) {

			Assert.assertTrue("Safari is not supporting in this OS",
					TestEnvironment.isPlatformWindows() || TestEnvironment.isPlatformMac());

			System.setProperty("webdriver.safari.driver",
					TestEnvironment.getDriversDirectory() + File.separator + "SafariDriver.safariextz");
			System.setProperty("webdriver.safari.noinstall", "true");

			log.info("initializing 'safari' driver...");
			driver = new SafariDriver();
		}

		return driver;
	}
}
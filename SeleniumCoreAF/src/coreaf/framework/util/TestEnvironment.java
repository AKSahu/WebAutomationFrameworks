package coreaf.framework.util;

import java.io.File;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This class models all the test environments
 * 
 * @author A. K. Sahu
 *
 */
public class TestEnvironment {

	static File classPathRoot = new File(System.getProperty("user.dir"));

	/**
	 * Gets screenshot directory
	 * 
	 * @return
	 */
	public static String getScreenshotDirectory() {
		File screenshot = new File(classPathRoot, "/screenshot");
		return screenshot.getAbsolutePath();
	}

	/**
	 * Gets driver location
	 * 
	 * @return
	 */
	public static String getDriversDirectory() {
		File screenshot = new File(ConfigUtil.getCoreAFLocation(), "/drivers");
		return screenshot.getAbsolutePath();
	}

	/**
	 * Gets driver location
	 * 
	 * @return
	 */
	public static String getConfigurationPropertiesDirectory() {
		File screenshot = new File(classPathRoot, "/config");
		return screenshot.getAbsolutePath();
	}

	/**
	 * Gets driver location
	 * 
	 * @return
	 */
	public static String getDataDirectory() {
		File screenshot = new File(classPathRoot, "/data");
		return screenshot.getAbsolutePath();
	}

	/**
	 * Gets logs location
	 * 
	 * @return
	 */
	public static String getLogsDirectory() {
		File screenshot = new File(classPathRoot, "/logs");
		return screenshot.getAbsolutePath();
	}

	/**
	 * Returns <code>true</code> if the current platform is Windows
	 * 
	 * @return
	 */
	public static boolean isPlatformWindows() {
		String os = getCurrentOperatingSystem();
		return os.contains("Windows");
	}

	/**
	 * Returns <code>true</code> if the current platform is Mac
	 * 
	 * @return
	 */
	public static boolean isPlatformMac() {
		String os = getCurrentOperatingSystem();
		return os.contains("Mac");
	}

	/**
	 * Gets the current running browser name
	 * 
	 * @return
	 */
	public static String getCurrentBrowserName(WebDriver driver) {
		Capabilities c = ((RemoteWebDriver) driver).getCapabilities();
		return c.getBrowserName();
	}

	/**
	 * Gets the current running browser version
	 * 
	 * @return
	 */
	public static String getCurrentBrowserVersion(WebDriver driver) {
		Capabilities c = ((RemoteWebDriver) driver).getCapabilities();
		return c.getVersion();
	}

	/**
	 * Gets the current operating system
	 * 
	 * @return
	 */
	public static String getCurrentOperatingSystem() {
		return System.getProperty("os.name");
	}

	/**
	 * Gets the current operating system
	 * 
	 * @return
	 */
	public static String getCurrentOperatingSystemArchitecture() {
		return System.getProperty("os.arch");
	}

	/**
	 * Gets the current java version
	 * 
	 * @return
	 */
	public static String getCurrentJavaVersion() {
		return System.getProperty("java.version");
	}
}

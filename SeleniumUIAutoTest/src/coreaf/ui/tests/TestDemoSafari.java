package coreaf.ui.tests;

import org.testng.annotations.Test;

import coreaf.framework.base.BasePage;
import coreaf.ui.base.TestBase;

/**
 * A test class to use parallel execution
 * 
 * @author A. K. Sahu
 *
 */
public class TestDemoSafari extends TestBase {

	@Test
	public void testMethod1() {
		BasePage.get("http://www.microsoft.com/");
	}

	@Test
	public void testMethod2() {
		BasePage.get("http://www.apple.com/");
	}
}

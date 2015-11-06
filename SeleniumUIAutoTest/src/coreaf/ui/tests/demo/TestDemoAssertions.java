package coreaf.ui.tests.demo;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import coreaf.framework.base.BasePage;
import coreaf.ui.base.TestBase;

/**
 * A test class to use parallel execution
 * 
 * @author A. K. Sahu
 *
 */
public class TestDemoAssertions extends TestBase {
	SoftAssert sAssert = new SoftAssert();
	
	@Test
	public void testMethod1() {
		BasePage.get("http://www.google.com");
		sAssert.assertEquals(BasePage.getTitle(), "Google ");
		BasePage.findElement(By.name("q")).sendKeys("aksahu blog");
		sAssert.assertEquals(BasePage.getTitle(), "Google Search");
		sAssert.assertAll();
	}

}

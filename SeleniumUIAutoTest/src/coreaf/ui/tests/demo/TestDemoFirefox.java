package coreaf.ui.tests.demo;

import org.testng.annotations.Test;

import coreaf.framework.base.BasePage;
import coreaf.ui.base.TestBase;

/**
 * A test class to use parallel execution
 * 
 * @author A. K. Sahu
 *
 */
public class TestDemoFirefox extends TestBase {

	@Test
	public void testMethod1() {
		BasePage.get("http://knowledgebase-wiki.appspot.com/");
	}

	@Test
	public void testMethod2() {
		BasePage.get("http://aksahu.blogspot.in/");
	}

}

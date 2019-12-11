
package modules;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

public class Assertion {

	/*
	 * Asserting the two Strings The result is recorded in Report if reports are
	 * enabled
	 */
	public static void assertEquals(String actual, String expected, ExtentTest extTest, String passMsg,String failMsg) {
		try {
			Assert.assertEquals(actual, expected);
			if (extentReports.xclude(extTest)) {
				extTest.pass(passMsg);
			}
		} catch (AssertionError error) {
			extTest.fail(failMsg);
		}
	}
	
	public static void assertTrue(boolean condition, ExtentTest extTest, String passMsg,String failMsg) {
		try {
			Assert.assertTrue(condition, failMsg);
			if (extentReports.xclude(extTest)) {
				extTest.pass(passMsg);
			}
		} catch (AssertionError error) {
			extTest.fail(failMsg);
		}
	}

}

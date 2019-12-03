
package modules;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

public class Assertion {
	
	/*
	 * Asserting the two Strings
	 * The result is recorded in Report if reports are enabled
	 */
	public static void assertEquals(String actual,String expected,ExtentTest exTest) {
		Assert.assertEquals(actual, expected);
		if(exTest==null) {
			System.out.println("Reports are Off");
		}else {
			exTest.pass("Test Pass");
		}
	}
	
}

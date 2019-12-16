
package modules;

import java.io.IOException;

import org.testng.Assert;
import org.testng.IReporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Assertion implements IReporter {

	/*
	 * Asserting the two Strings The result is recorded in Report if reports are
	 * enabled
	 */
	public static void assertEquals(String actual, String expected, ExtentTest extTest, String passMsg,
			String failMsg) {
		try {
			Assert.assertEquals(actual, expected);
			if (extentReports.xclude(extTest)) {
				extTest.log(Status.PASS, passMsg);
			}
			System.out.println(passMsg);

			/*
			 * Handling Assertion Error
			 */
		} catch (AssertionError error) {
			if (extentReports.xclude(extTest)) {
				/*
				 * Adding the Screen capture to the ExtentReports and handling it if file not
				 * found
				 */
				try {
					extTest.fail(failMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(screenshot.shot(failMsg)).build());
				} catch (IOException e) {
					System.out.println("Could NOT find the Screenshot");
				}
			}
			System.out.println(failMsg);
		}
	}

	public static void assertTrue(boolean condition, ExtentTest extTest, String passMsg, String failMsg) {
		try {
			Assert.assertTrue(condition, failMsg);
			if (extentReports.xclude(extTest)) {
				extTest.log(Status.PASS, passMsg);
			}
			System.out.println(passMsg);

			/*
			 * Handling Assertion Error
			 */
		} catch (AssertionError error) {
			if (extentReports.xclude(extTest)) {
				/*
				 * Adding the Screen capture to the ExtentReports and handling it if file not
				 * found
				 */
				try {
					extTest.fail(failMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(screenshot.shot(failMsg)).build());
				} catch (IOException e) {
					System.out.println("Could NOT find the Screenshot");
				}
			}
			System.out.println(failMsg);
		}
	}

	public static void assertContains(String actual, String expected, ExtentTest extTest, String passMsg,
			String failMsg) {
		try {
			Assert.assertTrue(actual.contains(expected));
			if (extentReports.xclude(extTest)) {
				extTest.log(Status.PASS, passMsg);
			}
			System.out.println(passMsg);

			/*
			 * Handling Assertion Error
			 */
		} catch (AssertionError error) {
			if (extentReports.xclude(extTest)) {
				/*
				 * Adding the Screen capture to the ExtentReports and handling it if file not
				 * found
				 */
				try {
					extTest.fail(failMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(screenshot.shot(failMsg)).build());
				} catch (IOException e) {
					System.out.println("Could NOT find the Screenshot");
				}
			}
			System.out.println(failMsg);
		}
	}

}

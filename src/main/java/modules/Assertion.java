
package modules;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Assertion {

	/*
	 * Asserting the two Strings The result is recorded in Report if reports are
	 * enabled
	 */
	public static void assertEquals(WebDriver driver, String actual, String expected, ExtentTest extTest, String passMsg,
			String failMsg) {
		try {
			Assert.assertEquals(actual, expected);
			extTest.log(Status.PASS, passMsg);
			System.out.println(passMsg);
			
			/*
			 * Handling Assertion Error
			 */
		} catch (AssertionError error) {
			/*
			 * Adding the Screen capture to the ExtentReports and handling it if file not
			 * found
			 */
			try {
				extTest.fail(failMsg, MediaEntityBuilder.createScreenCaptureFromPath(screenshot.shot(driver, failMsg)).build());
			} catch (IOException e) {
				System.out.println("Could NOT find the Screenshot");
			}
			System.out.println(failMsg);
		}
	}

	public static void assertTrue(WebDriver driver, boolean condition, ExtentTest extTest, String passMsg, String failMsg) {
		try {
			Assert.assertTrue(condition, failMsg);
			extTest.log(Status.PASS, passMsg);
			System.out.println(passMsg);

			/*
			 * Handling Assertion Error
			 */
		} catch (AssertionError error) {
			/*
			 * Adding the Screen capture to the ExtentReports and handling it if file not
			 * found
			 */
			try {
				extTest.fail(failMsg, MediaEntityBuilder.createScreenCaptureFromPath(screenshot.shot(driver, failMsg)).build());
			} catch (IOException e) {
				System.out.println("Could NOT find the Screenshot");
			}
			System.out.println(failMsg);
		}
	}

	public static void assertContains(WebDriver driver, String actual, String expected, ExtentTest extTest, String passMsg,
			String failMsg) {
		try {
			Assert.assertTrue(actual.contains(expected));
			extTest.log(Status.PASS, passMsg);
			System.out.println(passMsg);

			/*
			 * Handling Assertion Error
			 */
		} catch (AssertionError error) {
			/*
			 * Adding the Screen capture to the ExtentReports and handling it if file not
			 * found
			 */
			try {
				extTest.fail(failMsg, MediaEntityBuilder.createScreenCaptureFromPath(screenshot.shot(driver, failMsg)).build());
			} catch (IOException e) {
				System.out.println("Could NOT find the Screenshot");
			}
			System.out.println(failMsg);
		}
	}

}

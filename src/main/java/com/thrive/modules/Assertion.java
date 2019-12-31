
package com.thrive.modules;

import java.io.IOException;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.thrive.browserSetup.projectSetup;
import com.thrive.reportSetup.screenshot;

public class Assertion extends projectSetup{

	/*
	 * Asserting the two Strings The result is recorded in Report if reports are
	 * enabled
	 */
	public static void assertEquals(String actual, String expected, ExtentTest extTest, String passMsg) {
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
				extTest.fail(error.getCause(), MediaEntityBuilder.createScreenCaptureFromPath(screenshot.shot(error.getMessage())).build());
			} catch (IOException e) {
				System.out.println("Could NOT find the Screenshot");
			}
			System.out.println(error.getCause());
		}
	}

	public static void assertTrue(boolean condition, ExtentTest extTest, String passMsg) {
		try {
			Assert.assertTrue(condition);
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
				extTest.fail(error.getCause(), MediaEntityBuilder.createScreenCaptureFromPath(screenshot.shot(error.getMessage())).build());
			} catch (IOException e) {
				System.out.println("Could NOT find the Screenshot");
			}
			System.out.println(error.getCause());
		}
	}

	public static void assertContains(String actual, String expected, ExtentTest extTest, String passMsg) {
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
				extTest.fail(error.getCause(), MediaEntityBuilder.createScreenCaptureFromPath(screenshot.shot(error.getMessage())).build());
			} catch (IOException e) {
				System.out.println("Could NOT find the Screenshot");
			}
			System.out.println(error.getCause());
		}
	}

}

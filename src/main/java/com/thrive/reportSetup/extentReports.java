package com.thrive.reportSetup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.thrive.browserSetup.projectSetup;
import com.thrive.utils.Property;
import com.thrive.utils.auto_constant;

public class extentReports extends projectSetup implements auto_constant {
	private static ExtentReports extent = new ExtentReports();
	public static ExtentHtmlReporter reporter;
	private static ExtentTest extTest;
	private static ExtentTest childTest;
	public static String reportPath = Property.getProperty("extentPath") + reportDate + ".html";

	/*
	 * Generates the Report file in the destination and attach the report in the
	 * html file
	 */
	public static void attachReport() {

		// Setting the Environment in the Extent Reports
		extent.setSystemInfo("User", "Vinay M S");
		extent.setSystemInfo("Operating System", "Ubuntu Linux");
		extent.setSystemInfo("Java version", "Jdk 1.8.0");
		extent.setSystemInfo("Browsers", "Chrome & Firefox");

		// Attaching the Html Report to the extent reference and Configuring the Report
		reporter = new ExtentHtmlReporter(reportPath);
		reporter.config().setCSS(".r-img { width: 30%; }");
		reporter.config().setDocumentTitle("Automation Test Report");
		reporter.config().setReportName("TO Test Report");
		reporter.config().setTheme(Theme.DARK);

		extent.attachReporter(reporter);
	}

	/*
	 * Sets the Extent test
	 */
	public static void setExtentTest(String testName) {
		extTest = extent.createTest(testName);
	}
	
	/*
	 * Sets the Child Test
	 */
	public static void setChildTest(String childName) {
		childTest = extTest.createNode(childName);
	}
	
	/*
	 * Returns the Extent Test reference
	 */
	public static ExtentTest getExtentTest() {
		return extTest;
	}
	
	/*
	 * Returns Child Test reference
	 */
	public static ExtentTest getChildTest() {
		return childTest;
	}
	
	/*
	 * Returns the ExtentReports reference
	 */
	public static ExtentReports getExtent() {
		return extent;
	}

}
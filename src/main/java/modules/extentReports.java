package modules;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReports implements auto_constant {
	public static ExtentReports extent = new ExtentReports();
	public static ExtentHtmlReporter reporter;
	public static ExtentTest extTest;

	/*
	 * Generates the Report file in the destination and attach the report in the
	 * html file
	 */
	
	public static void attachReport() {
		extent.setSystemInfo("User", "Vinay M S");
		extent.setSystemInfo("Operating System", "Ubuntu Linux");
		extent.setSystemInfo("Java version", "Jdk 1.8.0");
		extent.setSystemInfo("Browsers", "Chrome & Firefox");
		
		reporter = new ExtentHtmlReporter(extentPath + "/Report " + dateFunc.getReportDate() + ".html");
		reporter.config().setCSS(".r-img { width: 30%; }");
		reporter.config().setDocumentTitle("Automation Test Report");
		reporter.config().setReportName("TO Test Report");
		reporter.config().setTheme(Theme.DARK);
		
		extent.attachReporter(reporter);
	}

	/*
	 * Creates a new Extent test and returns the extentTest object
	 */
	public static ExtentTest extentTest(String testName) {
		extTest = extent.createTest(testName);
		return extTest;
	}

}
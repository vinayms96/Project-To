package modules;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class extentReports extends openBrowser implements auto_constant{
	static ExtentReports extent = new ExtentReports();
	
	/*
	 * Generates the Report file in the destination and attach the report in the html file
	 */
	public static void attRepo() {
		if(Property.getProperty("extent").equalsIgnoreCase("on")) {
			extent.setSystemInfo("User", "Vinay M S");
			extent.setSystemInfo("Operating System", "Ubuntu Linux");
			extent.setSystemInfo("Java version", "Jdk 1.8.0");
			extent.setAnalysisStrategy(AnalysisStrategy.TEST);
			extent.setSystemInfo("Browsers", "Chrome & Firefox");
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(extentPath+"/Report "+dateFunc.getReportDate()+".html");
			reporter.config().setCSS(".r-img { width: 30%; }");
			extent.attachReporter(reporter);
		}
	}
	
	/*
	 * Creates a new Extent test and returns the extentTest object
	 */
	public static ExtentTest extentTest() {
	
		if(Property.getProperty("extent").equalsIgnoreCase("on")) {
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			ExtentTest child = parentTest.get().createNode(stackTrace[2].getMethodName());
			return child;
		}else {
			return null;
		}
		
	}
	
	/*
	 * Verifying if the Extent Reports are Turned off or On
	 * If On returns true to Assertion class else returns false
	 */
	public static boolean xclude(ExtentTest extTest) {
		if(extTest==null) {
//			System.out.println("Reports are Off");
			return false;
		}
		return true;
	}
	
}
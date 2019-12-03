package modules;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class extentReports implements auto_constant{
	static ExtentReports extent = new ExtentReports();	
	
	public static void attRepo(String browser) {
		if(Property.getProperty("extent").equalsIgnoreCase("on")) {
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(extentPath+browser+"/"+dateFunc.getDate()+"test.html");
			extent.attachReporter(reporter);
		}
	}
	
	public static ExtentTest exTest(String pageName,String testName) {
	
		if(Property.getProperty("extent").equalsIgnoreCase("on")) {
			return extent.createTest(testName);
		}else {
			return null;
		}
	}
	
	public static void xclude(String actual,String expected,ExtentTest exTest) {
		if(exTest==null) {
			System.out.println("Reports are Off");
		}else {
			exTest.info("Test Pass");
		}
	}
	
}
package testCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import modules.extentReports;
import modules.openBrowser;
import pageModels.homePage;
import pageModels.signup_login_Page;

public class testClass extends openBrowser{
	ExtentTest extTest;
	
	@Test(groups = {"exe.home"})
	public void base() throws Exception {
		
		/*
		 * Homepage Scenarios
		 */
		homePage hp = new homePage(driver);
		extTest = extentReports.exTest("Homepage", "Homepage Test Status");
		hp.loginLink(extTest);
		
		/*
		 * Signup / login page scenarios
		 */
		extTest = extentReports.exTest("Signup/Login Page", "Check the Login and Signup Func.");
		signup_login_Page slp = new signup_login_Page(driver);
		slp.checkError(extTest);
		slp.signin(extTest);
		
	}
	
}

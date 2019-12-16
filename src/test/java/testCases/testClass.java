package testCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import modules.openBrowser;
import pageModels.homePage;
import pageModels.signup_login_Page;

public class testClass extends openBrowser {
	ExtentTest extTest;

	@Test(groups = { "exe.home" })
	public void base() throws Exception {

		/*
		 * Homepage Scenarios
		 */
		homePage hp = new homePage(driver);
		hp.loginLink();

		/*
		 * Signup / login page scenarios
		 */
		signup_login_Page slp = new signup_login_Page(driver);
		slp.checkError();
		slp.signin();

	}

}

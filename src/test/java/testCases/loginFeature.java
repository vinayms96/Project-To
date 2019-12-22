package testCases;

import org.testng.annotations.Test;

import modules.extentReports;
import modules.projectSetup;
import pageModels.homePage;
import pageModels.signup_login_Page;

public class loginFeature extends projectSetup {

	@Test
	public void loginTest() throws Exception {
		/*
		 * Page model objects
		 */
		homePage hp = new homePage(driver);
		signup_login_Page slp = new signup_login_Page(driver);
		
		extentReports.extTest = extentReports.extentTest(projectSetup.extBrowser + ": loginFeature");

		/*
		 * Checking the error msg box
		 */
		hp.loginLink();
		slp.errorMsgBox();
		
		/*
		 * Checks the Email and Password field errors
		 */
//		hp.loginLink();
		slp.textFieldError();

		/*
		 * Verifies with valid login credentials
		 */
//		hp.loginLink();
		slp.signin();

	}

}

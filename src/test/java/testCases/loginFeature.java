package testCases;

import org.testng.annotations.Test;

import modules.extentReports;
import modules.projectSetup;
import pageModels.homePage;
import pageModels.signup_login_Page;

public class loginFeature extends projectSetup {

	@Test(description = "Checking the functionality of the Login Feature")
	public void loginTest() throws Exception {

		// Page model objects
		homePage hp = new homePage(driver);
		signup_login_Page slp = new signup_login_Page(driver);
		
		extentReports.extTest = extentReports.extentTest(projectSetup.extBrowser + ": Login Feature");

		// Checking the error msg box
		hp.clickLoginLink();
		slp.errorMsgBox();
		
		// Checks the Email and Password field errors
		slp.textFieldError();

		// Verifies with valid login credentials
		slp.loginValidCred();

	}

}

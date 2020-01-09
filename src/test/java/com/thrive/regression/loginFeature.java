package com.thrive.regression;

import org.testng.annotations.Test;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.pageModels.home_page;
import com.thrive.pageModels.login_page;
import com.thrive.pageModels.privacy_policy;
import com.thrive.reportSetup.ExtentReports;

public class loginFeature extends ProjectSetup {

	@Test(description = "Checking the functionality of the Login Feature")
	public void loginTest() throws Exception {

		// Page model objects
		home_page hp = new home_page(driver);
		login_page slp = new login_page(driver);
		privacy_policy privacy = new privacy_policy(driver);

		ExtentReports.setExtentTest(ProjectSetup.extBrowser + ": Login Feature");

		// Check if Privacy Policy is displayed
		privacy.policy_check();

		hp.clickLoginLink();

		// Checking the error msg box
		slp.errorMsgBox();

		// Checks the Email and Password field errors
		slp.textFieldError();

		// Verifies with valid login credentials
		slp.everyFieldErrorCheck();
		slp.loginValidCred();

	}

}

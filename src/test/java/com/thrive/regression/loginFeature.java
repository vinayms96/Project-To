package com.thrive.regression;

import org.testng.annotations.Test;

import com.thrive.browserSetup.projectSetup;
import com.thrive.pageModels.homePage;
import com.thrive.pageModels.signup_login_Page;
import com.thrive.reportSetup.extentReports;

public class loginFeature extends projectSetup {

	@Test(description = "Checking the functionality of the Login Feature")
	public void loginTest() throws Exception {

		// Page model objects
		extentReports report = new extentReports();
		homePage hp = new homePage(driver);
		signup_login_Page slp = new signup_login_Page(driver);
		
		report.setExtentTest(projectSetup.extBrowser + ": Login Feature");

		// Checking the error msg box
		hp.clickLoginLink();
		slp.errorMsgBox();
		
		// Checks the Email and Password field errors
		slp.textFieldError();

		// Verifies with valid login credentials
		slp.everyFieldErrorCheck();
		slp.loginValidCred();
		
	}

}

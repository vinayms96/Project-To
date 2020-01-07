package com.thrive.regression;

import org.testng.annotations.Test;

import com.thrive.browserSetup.projectSetup;
import com.thrive.pageModels.homePage;
import com.thrive.reportSetup.extentReports;

public class verifyLinks extends projectSetup {

	@Test(description = "Verifing the links redirection")
	public void verifyAllLinks() {

		// Page object references
		extentReports report = new extentReports();
		homePage hp = new homePage(driver);

		// Created Extent Test reference
		report.setExtentTest(projectSetup.extBrowser + ": Verify Links");

		// Calling the homepage methods
		hp.checkMenuLinks();

	}

}

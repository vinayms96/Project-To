package com.thrive.regression;

import org.testng.annotations.Test;

import com.thrive.browserSetup.projectSetup;
import com.thrive.pageModels.homePage;
import com.thrive.reportSetup.extentReports;

public class verifyLinks extends projectSetup {

	@Test(description = "Verifing the links redirection")
	public void verifyAllLinks() {
		// Created Extent Test reference
		extentReports.extTest = extentReports.extent.createTest(projectSetup.extBrowser + ": Verify Links");

		// Homepage reference
		homePage hp = new homePage(driver);

		// Calling the homepage methods
		hp.checkMenuLinks();

	}

}

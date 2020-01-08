package com.thrive.regression;

import org.testng.annotations.Test;

import com.thrive.browserSetup.projectSetup;
import com.thrive.pageModels.home_page;
import com.thrive.reportSetup.extentReports;

public class verifyLinks extends projectSetup {

	@Test(description = "Verifing the links redirection")
	public void verifyAllLinks() {

		// Page object references
		home_page hp = new home_page(driver);

		// Created Extent Test reference
		extentReports.setExtentTest(projectSetup.extBrowser + ": Verify Links");

		// Calling the homepage methods
		hp.checkMenuLinks();

	}

}

package com.thrive.regression;

import org.testng.annotations.Test;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.pageModels.home_page;
import com.thrive.reportSetup.extentReports;

public class verifyLinks extends ProjectSetup {

	@Test(description = "Verifing the links redirection")
	public void verifyAllLinks() {

		// Page object references
		home_page hp = new home_page(driver);

		// Created Extent Test reference
		extentReports.setExtentTest(ProjectSetup.extBrowser + ": Verify Links");

		// Calling the homepage methods
		hp.checkMenuLinks();

	}

}

package testCases;

import org.testng.annotations.Test;

import modules.extentReports;
import modules.projectSetup;
import pageModels.homePage;

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

package com.thrive.regression;

import com.thrive.browserSetup.ProjectSetup;
import org.testng.annotations.Test;

import com.thrive.pageModels.home_page;
import com.thrive.reportSetup.ExtentReports;

public class VerifyLinks extends ProjectSetup {

    @Test(description = "Verifing the links redirection")
    public void verifyAllLinks() {

        // Page object references
        home_page hp = new home_page(driver);

        // Created Extent Test reference
        ExtentReports.setExtentTest(ProjectSetup.extBrowser + ": Verify Links");

        // Calling the homepage methods
        hp.checkMenuLinks();

    }

}

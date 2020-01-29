package com.thrive.regression;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import com.thrive.reportSetup.ExtentReports;
import org.testng.annotations.Test;

import com.thrive.pageModels.home_page;

public class VerifyLinks extends ProjectSetup {

    @Test(description = "Verifying the links redirection", groups = {"links.menu"})
    public void verifyAllLinks() {

        // Page object references
        home_page hp = new home_page(driver);

        // Created Extent Test reference and Logger is set
        ExtentReports.setExtentTest("Verify Links");
        LoggerConfig.setLogger(getClass().getName());

        // Calling the homepage methods
        hp.check_menu_links();

    }

}
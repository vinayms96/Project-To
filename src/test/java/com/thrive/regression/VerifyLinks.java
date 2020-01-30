package com.thrive.regression;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import com.thrive.pageModels.home_page;
import com.thrive.reportSetup.ExtentReports;
import org.testng.annotations.Test;

public class VerifyLinks extends ProjectSetup {

    @Test(description = "Verifying the links redirection", groups = {"links.menu"})
    public void verifyAllLinks() throws InterruptedException {

        // Page object references
        home_page hp = new home_page(driver);

        // Created Extent Test reference and Logger is set
        ExtentReports.setExtentTest("Verifying Menu Links");
        LoggerConfig.setLogger(getClass().getName());

        // Calling the homepage methods
        hp.check_menu_links();

    }

    @Test(description = "Verifying the Footer Links", groups = {"links.footer"})
    public void footer_links() throws InterruptedException {

        // Page Object references
        home_page home = new home_page(driver);

        // Created Extent Test reference and Logger is set
        ExtentReports.setExtentTest("Verifying Footer Links");
        LoggerConfig.setLogger(getClass().getName());

        // Calling the Page Objects
        home.foot_link_check();

    }

}

package com.thrive.regression;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import com.thrive.pageModels.home_page;
import com.thrive.pageModels.login_page;
import com.thrive.pageModels.privacy_policy;
import com.thrive.reportSetup.ExtentReports;
import com.thrive.utils.ExcelUtils;
import com.thrive.utils.Property;
import org.testng.annotations.Test;

public class PrivacyPolicy extends ProjectSetup {

    @Test(description = "Validate the Privacy Policy cookie", groups = {"privacy.login"})
    public void validate_privacy_login() throws Exception {

        // Setting up Extent Test
        ExtentReports.setExtentTest("Validate Privacy Login");
        LoggerConfig.setLogger(getClass().getName());

        //Creating the Page Objects
        home_page home = new home_page(driver);
        login_page login = new login_page(driver);
        privacy_policy policy = new privacy_policy(driver);

        //Click on Sign in link
        home.click_login_link();
        // Enter the Login fields and click on Submit
        login.login_cred(ExcelUtils.getData(Property.getProperty("validCreds")).get("email"),
                ExcelUtils.getData(Property.getProperty("validCreds")).get("password"));
        // Check if Privacy popup is displayed
        policy.check_popup();
        // Clear the Email and Password Box
        login.email_box().clear();
        login.pass_box().clear();
        // Click on Login button without entering any fields
        login.submit();
        // Check if Privacy popup is displayed
        policy.check_popup();

    }

}

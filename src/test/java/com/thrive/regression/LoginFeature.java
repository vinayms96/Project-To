package com.thrive.regression;

import com.thrive.browserSetup.ProjectSetup;
import org.testng.annotations.Test;

import com.thrive.pageModels.home_page;
import com.thrive.pageModels.login_page;
import com.thrive.pageModels.privacy_policy;
import com.thrive.reportSetup.ExtentReports;

public class LoginFeature extends ProjectSetup {

    @Test(description = "Checking the functionality of the Login Feature")
    public void field_verification() throws Exception {

        // Created Extent Test reference
        ExtentReports.setExtentTest(ProjectSetup.extBrowser + ": Login Feature");

        // Page model objects
        home_page hp = new home_page(driver);
        login_page slp = new login_page(driver);
        privacy_policy privacy = new privacy_policy(driver);

        // Check if Privacy Policy is displayed
        privacy.policy_check();
        // Clicks on Sign in link
        hp.clickLoginLink();
        // Checking the error msg box
        slp.errorMsgBox();
        // Checks the Email and Password field errors
        slp.textFieldError();
        // Checks the Field error individually
        slp.everyFieldErrorCheck();

    }

    @Test(description = "User validation is performed")
    public void user_validation() throws Exception {

        // Setting the Extent test reference
        ExtentReports.setExtentTest("Login Validation");

        // Page model objects
        home_page hp = new home_page(driver);
        login_page slp = new login_page(driver);
        privacy_policy privacy = new privacy_policy(driver);

        // Check if Privacy Policy is displayed
        privacy.policy_check();
        // Click on Sign-in link
        hp.clickLoginLink();
        // Verifies with valid login credentials
        slp.loginValidCred();
    }

}

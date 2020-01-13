package com.thrive.regression;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import com.thrive.utils.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.thrive.pageModels.home_page;
import com.thrive.pageModels.login_page;
import com.thrive.pageModels.privacy_policy;
import com.thrive.reportSetup.ExtentReports;

public class LoginFeature extends ProjectSetup {

    @Test(description = "Checking the functionality of the Login Feature")
    public void field_verification() throws Exception {

        // Created Extent Test reference and Logger is set
        ExtentReports.setExtentTest(ProjectSetup.extBrowser + ": Login Feature");
        LoggerConfig.setLogger(getClass().getName());

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

    @Test(description = "User validation is performed", dataProvider = "getUserData")
    public void user_validation(String email, String pass) throws Exception {

        // Setting the Extent test reference and Logger is set
        ExtentReports.setExtentTest(ProjectSetup.extBrowser + ": Login Validation");
        LoggerConfig.setLogger(getClass().getName());

        // Page model objects
        home_page hp = new home_page(driver);
        login_page slp = new login_page(driver);
        privacy_policy privacy = new privacy_policy(driver);

        // Check if Privacy Policy is displayed
        privacy.policy_check();
        // Click on Sign-in link
        hp.clickLoginLink();

        // Verifies with valid login credentials
        slp.loginCred(email, pass);

    }

    @DataProvider
    public Object[][] getUserData() {
        Object[][] obj = new Object[2][2];
        obj[0][0] = ExcelUtils.getData("invalidLogin").get(3);
        obj[0][1] = ExcelUtils.getData("invalidLogin").get(4);
        obj[1][0] = ExcelUtils.getData("validLogin").get(3);
        obj[1][1] = ExcelUtils.getData("validLogin").get(4);
        return obj;
    }
}

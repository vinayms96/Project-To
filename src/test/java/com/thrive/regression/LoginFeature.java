package com.thrive.regression;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import com.thrive.modules.WaitUntil;
import com.thrive.pageModels.home_page;
import com.thrive.pageModels.login_page;
import com.thrive.pageModels.privacy_policy;
import com.thrive.reportSetup.ExtentReports;
import com.thrive.utils.ExcelUtils;
import com.thrive.utils.Property;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginFeature extends ProjectSetup {

    @Test(description = "Checking the functionality of the Login Feature", groups = {"login.field"}, priority = 0)
    public void field_verification() throws Exception {

        // Created Extent Test reference and Logger is set
        ExtentReports.setExtentTest("Field Verification");
        LoggerConfig.setLogger(getClass().getName());

        // Page model objects
        home_page hp = new home_page(driver);
        login_page slp = new login_page(driver);
        privacy_policy privacy = new privacy_policy(driver);

        // Check if Privacy Policy is displayed
        privacy.policy_accept();
        // Clicks on Sign in link
        hp.click_login_link();
        // Checking the error msg box
        slp.error_msg_box();
        // Checks the Email and Password field errors
        slp.text_field_error();
        // Checks the Field error individually
        slp.each_field_error();

    }

    @Test(description = "User validation is performed", dataProvider = "getUserData", groups = {"login.user"},
            priority = 1)
    public void user_validation(String email, String pass) throws Exception {

        // Setting the Extent test reference and Logger is set
        ExtentReports.setExtentTest("Login Validation");
        LoggerConfig.setLogger(getClass().getName());

        // Page model objects
        home_page hp = new home_page(driver);
        login_page slp = new login_page(driver);
        privacy_policy privacy = new privacy_policy(driver);
        
        // Click on Sign-in link
        hp.click_login_link();
        // Verifies with valid login credentials
        slp.login_cred(email, pass);

        try {
            WaitUntil.waitRefresh(5, slp.wrong_error());
            Assert.assertEquals(slp.wrong_error().getAttribute("innerHTML"),
                    ExcelUtils.getData("invalidLogin").get("error_box"));

            // Result printed in Extent Reports and Logged
            ExtentReports.getChildTest().pass("Invalid Credentials error message is displayed");
            LoggerConfig.getLogger().info("Invalid Credentials error message is displayed");
        } catch (Exception e) {
            String fullName = ExcelUtils.getData("validLogin").get("first_name") + " " + ExcelUtils.getData("validLogin").get("last_name");

            // Waiting for the username to be displayed in the Header
            Thread.sleep(5000);

            // Verifying if the User name is properly displayed
            Assert.assertEquals(slp.user_fullname().getText(), fullName);

            // Result printed in Extent Reports and Logged
            ExtentReports.getChildTest().pass("Logged in Successfully");
            LoggerConfig.getLogger().info("Logged in Successfully");
        }

    }

    @DataProvider
    public Object[][] getUserData() {
        Object[][] obj = new Object[2][2];
        obj[0][0] = ExcelUtils.getData("invalidLogin").get("email");
        obj[0][1] = ExcelUtils.getData("invalidLogin").get("password");
        obj[1][0] = ExcelUtils.getData("validLogin").get("email");
        obj[1][1] = ExcelUtils.getData("validLogin").get("password");
        return obj;
    }

    @Test(description = "Deleting the session cookie and check user session", groups = {"login.session"}, priority = 2)
    public void user_session() throws Exception {

        // Setting the Extent test reference and Logger is set
        ExtentReports.setExtentTest("User Session");
        LoggerConfig.setLogger(getClass().getName());

        // Page Model Objects
        home_page home = new home_page(driver);
        login_page slp = new login_page(driver);

        // Clicks on Login link
        home.click_login_link();
        // Logs into user account
        slp.login_cred(ExcelUtils.getData("validLogin").get("email"),
                ExcelUtils.getData("validLogin").get("password"));
        // Deleting cookie and comparing the title
        slp.user_session();

    }
}


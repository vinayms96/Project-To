package com.thrive.pageModels;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import com.thrive.modules.Action;
import com.thrive.screenshot.Screenshot;
import com.thrive.modules.WaitUntil;
import com.thrive.reportSetup.ExtentReports;
import com.thrive.utils.ExcelUtils;
import com.thrive.utils.Property;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

public class login_page extends ProjectSetup {
    int count;
    JavascriptExecutor js = (JavascriptExecutor)driver;

    /*
     * Login page Elements
     */
    @FindBy(xpath = "//div[@class='block-content']/form/fieldset/div[3]/div/button/span")
    private WebElement sign_Submit;
    @FindBy(xpath = "//div[@id='email-error']")
    private WebElement email_error;
    @FindBy(xpath = "//div[@id='pass-error']")
    private WebElement pass_error;
    @FindBy(xpath = "//div[@class='header content']/div[2]/span/button/span")
    private WebElement userName;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailBox;
    @FindBy(xpath = "(//input[@id='pass'])[1]")
    private WebElement passBox;
    @FindBy(xpath = "//div[@class='messages']/div/div")
    private WebElement emptyLogErr;
    @FindBy(xpath = "//div[@role='alert']/div/div")
    private WebElement wrongLogin;

    /*
     * Constructor Sets the driver to Current page
     */
    public login_page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /*
        Click on Login button
     */
    public void submit(){
        ExtentReports.setChildTest("Submit Button");

        // Click on Submit button
        Action.moveClick(sign_Submit);
        // Logs and Extent report
        ExtentReports.getChildTest().info("Login Button Clicked");
        LoggerConfig.getLogger().info("Login Button Clicked");
    }

    /*
        Returns Error message if Wrong credentials are entered
     */
    public WebElement wrong_error(){
        return wrongLogin;
    }

    /*
        Returns Users fullname after login
     */
    public WebElement user_fullname(){
        return userName;
    }

    /*
        Return Email box WebElement
     */
    public WebElement email_box(){
        return emailBox;
    }

    /*
        Return Password Box WebElement
     */
    public WebElement pass_box(){
        return passBox;
    }

    /*
     * Checking error message displayed in Message block
     */
    public void error_msg_box() {

        // Extent Report Child node created
        ExtentReports.setChildTest("Error Message Box");

        try {
            Action.moveClick(sign_Submit);

            LoggerConfig.getLogger().info("Clicked on Login Submit button");

            WaitUntil.waitVisible(5, emptyLogErr);

            // Comparing Error message
            Assert.assertEquals(emptyLogErr.getAttribute("innerHTML"), ExcelUtils.getData(Property.getProperty("validCreds")).get(6));
            System.out.println("Login and Password error message is displayed");

            // Result printed in Extent Reports and Logged
            ExtentReports.getChildTest().pass("Login and Password error message is displayed");
            LoggerConfig.getLogger().info("Login and Password error message is displayed");

            count++;
        } catch (AssertionError e) {
            try {
                ExtentReports.getChildTest().fail(e.getCause(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.shot()).build());
                LoggerConfig.getLogger().error(e.getCause());
            } catch (IOException e1) {
                e1.getCause();
            }
        }

    }

    /*
     * Checks if error messages are displayed when fields are empty
     */
    public void text_field_error() throws Exception {

        // Extent Report Child node created
        ExtentReports.setChildTest("Text Field error message");

        // Checking error message displayed in individual input boxes
        if (count == 1) {
            sign_Submit.click();
            LoggerConfig.getLogger().info("Login Submit button is clicked");
        }
        WaitUntil.waitVisible(5, email_error);

        // Comparing Email Filed Error Messages
        Assert.assertEquals(email_error.getText(), ExcelUtils.findRowData(Property.getProperty("validCreds")).get(5));

        // Result is printed in extent report and Logged
        ExtentReports.getChildTest().pass("Proper error msg for Email Field is displayed");
        LoggerConfig.getLogger().info("Proper error msg for Email Field is displayed");

        // Comparing Password Filed Error Messages
        Assert.assertEquals(pass_error.getText(), ExcelUtils.findRowData(Property.getProperty("validCreds")).get(5));

        // Result is printed in extent report and Logged
        ExtentReports.getChildTest().pass("Proper error msg for Password Field is displayed");
        LoggerConfig.getLogger().info("Proper error msg for Password Field is displayed");

    }

    /*
     * Login with valid credentials Checks the header for username to verify
     * Successful login
     */
    public void login_cred(String email, String password) throws Exception {

        // Extent Report Child node created
        ExtentReports.setChildTest("Login with User Credentials");

        // Entering the values to the Fields
        emailBox.sendKeys(email);
        passBox.sendKeys(password);
        LoggerConfig.getLogger().info("Email id and Password entered to the Fields");

        // Clicks on Submit button
        Action.moveClick(sign_Submit);
        LoggerConfig.getLogger().info("Clicked on Login Submit button");

    }

    /*
     * Verifying the Field Error messages for individual fields
     */
    public void each_field_error() {

        // Extent Report Child node created
        ExtentReports.setChildTest("Individual Login Field Error Msg");

        // verify the error msg displayed in Password field
        emailBox.sendKeys(ExcelUtils.getData(Property.getProperty("validCreds")).get(3));
        LoggerConfig.getLogger().info("Email id entered in the field");

        Action.moveClick(sign_Submit);
        LoggerConfig.getLogger().info("Clicked on Login Submit button");

        // Compare the Error message
        Assert.assertEquals(pass_error.getText(), ExcelUtils.getData(Property.getProperty("validCreds")).get(5));

        // Result is printed in Extent Reports and Logged
        ExtentReports.getChildTest().pass("Error message is displayed for Password Field");
        LoggerConfig.getLogger().info("Error message is displayed for Password Field");

        emailBox.clear();
        LoggerConfig.getLogger().info("Email field text is cleared");

        // Verify the error msg displayed in Email field
        passBox.sendKeys(ExcelUtils.getData(Property.getProperty("validCreds")).get(4));
        LoggerConfig.getLogger().info("Password entered in the field");

        Action.moveClick(sign_Submit);
        LoggerConfig.getLogger().info("Clicked on Login Submit button");

        // Compare the Error message
        Assert.assertEquals(email_error.getText(), ExcelUtils.getData(Property.getProperty("validCreds")).get(5));

        // Result printed in Extent report and logged
        ExtentReports.getChildTest().pass("Error message is displayed for Email Field");
        LoggerConfig.getLogger().info("Error message is displayed for Email Field");

        passBox.clear();
        LoggerConfig.getLogger().info("Password field text is cleared");

    }
}
package com.thrive.pageModels;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.thrive.browserSetup.ProjectSetup;
import com.thrive.modules.Action;
import com.thrive.modules.Screenshot;
import com.thrive.modules.Wait;
import com.thrive.reportSetup.ExtentReports;
import com.thrive.utils.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.io.IOException;

public class login_page extends ProjectSetup {
    int count;

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

    /*
     * Constructor Sets the driver to Current page
     */
    public login_page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /*
     * Checking error message displayed in Message block
     */
    public void errorMsgBox() {

        try {
            // Extent Report Child node created
            ExtentReports.setChildTest("Error Message Box");

            Action.moveClick(sign_Submit);

            Wait.waitVisible(5, emptyLogErr);

            // Comparing Error message
            Assert.assertEquals(emptyLogErr.getAttribute("innerHTML"), ExcelUtils.getData("login").get(6));
            System.out.println("Login and Password error message is displayed");
            ExtentReports.getChildTest().pass("Login and Password error message is displayed");

            count++;
        } catch (Exception e) {
            try {
                System.out.println(e.getCause());
                ExtentReports.getChildTest().fail(e.getCause(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.shot()).build());
            } catch (IOException e1) {
                e1.getCause();
            }
        }

    }

    /*
     * Checks if error messages are displayed when fields are empty
     */
    public void textFieldError() throws Exception {

        // Extent Report Child node created
        ExtentReports.setChildTest("Text Field error message");

        // Checking error message displayed in individual input boxes
        if (count == 1) {
            sign_Submit.click();
        }
        Wait.waitVisible(5, email_error);

        // Comparing Error Messages
        Assert.assertEquals(email_error.getText(), ExcelUtils.findRowData("login").get(5));
        System.out.println("Proper error msg for Email Field is displayed");
        ExtentReports.getChildTest().pass("Proper error msg for Email Field is displayed");

        Assert.assertEquals(pass_error.getText(), ExcelUtils.findRowData("login").get(5));
        System.out.println("Proper error msg for Password Field is displayed");
        ExtentReports.getChildTest().pass("Proper error msg for Password Field is displayed");

    }

    /*
     * Login with valid credentials Checks the header for username to verify
     * Successful login
     */
    public void loginValidCred() throws Exception {

        // Extent Report Child node created
        ExtentReports.setChildTest("Login with Valid Credentials");

        // Fetching the data from Excel sheet and entering the values
        int cell = 3;
        for (int i = 1; i <= 2; i++) {
            driver.findElement(By.xpath("(//form[@class='form form-login']/fieldset/div/div)[" + i + "]/input"))
                    .sendKeys(ExcelUtils.getData("login").get(cell++));
        }

        Action.moveClick(sign_Submit);

        String fullName = ExcelUtils.getData("login").get(1) + " " + ExcelUtils.getData("login").get(2);

        // Waiting for the username to be displayed in the Header
        Thread.sleep(5000);

        // Verifying if the User name is properly displayed
        Assert.assertEquals(userName.getText(), fullName);
        System.out.println("Logged in Successfully");
        ExtentReports.getChildTest().pass("Logged in Successfully");
    }

    /*
     * Verifying the Field Error messages for individual fields
     */
    public void everyFieldErrorCheck() {

        // Extent Report Child node created
        ExtentReports.setChildTest("Individual Login Field Error Msg");

        // verify the error msg displayed in Password field
        emailBox.sendKeys(ExcelUtils.getData("login").get(3));
        Action.moveClick(sign_Submit);

        // Compare the Error message
        Assert.assertEquals(pass_error.getText(), ExcelUtils.getData("login").get(5));
        System.out.println("Error message is displayed for Password Field");
        ExtentReports.getChildTest().pass("Error message is displayed for Password Field");

        emailBox.clear();

        // Verify the error msg displayed in Email field
        passBox.sendKeys(ExcelUtils.getData("login").get(4));
        Action.moveClick(sign_Submit);

        // Compare the Error message
        Assert.assertEquals(email_error.getText(), ExcelUtils.getData("login").get(5));
        System.out.println("Error message is displayed for Email Field");
        ExtentReports.getChildTest().pass("Error message is displayed for Email Field");

        passBox.clear();

    }
}
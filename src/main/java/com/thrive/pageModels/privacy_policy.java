package com.thrive.pageModels;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.thrive.browserSetup.ProjectSetup;
import com.thrive.logger.LoggerConfig;
import com.thrive.modules.Action;
import com.thrive.modules.WaitUntil;
import com.thrive.reportSetup.ExtentReports;
import com.thrive.screenshot.Screenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

public class privacy_policy extends ProjectSetup {

    // Page Elements
    @FindBy(id = "gdpr-notice-cookie-block")
    private WebElement policyBlock;
    @FindBy(xpath = "//div[@id='gdpr-notice-cookie-block']/div/p/a")
    private WebElement learnMore;
    @FindBy(xpath = "//div[@id='gdpr-notice-cookie-block']/div/div/button/span")
    private WebElement accept;
    @FindBy(xpath = "//aside //h1")
    private WebElement popup;
    @FindBy(xpath = "//aside //footer/button/span")
    private WebElement pop_accept;

    /*
     * Page Constructor Constructor Sets the driver to Current page
     */
    public privacy_policy(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /*
        Returns Privacy Footer Popup WebElement
     */
    public WebElement privacy_block(){
        return policyBlock;
    }

    /*
     * Check if Privacy Policy popup is displayed if yes then its closed
     */
    public void policy_accept() {

        // Extent Report Child node created
        ExtentReports.setChildTest("Policy Popup Check");

        WaitUntil.waitRefresh(5, policyBlock);
        if (policyBlock.isDisplayed()) {
            Action.moveClick(accept);
            ExtentReports.getChildTest().info("Privacy Policy is Accepted");
            LoggerConfig.getLogger().info("Privacy Policy is Accepted");
        } else {
            try {
                ExtentReports.getChildTest().info("Privacy Policy is not Displayed",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.shot()).build());
                LoggerConfig.getLogger().info("Privacy Policy is not Displayed");
            } catch (IOException e) {
                e.printStackTrace();
            }
            LoggerConfig.getLogger().info("Privacy Policy is not Accepted");
            Assert.assertTrue(false, "Privacy Policy is not Displayed");
        }
    }

    /*
     * Checks if Privacy Popup is displayed or not
     * If displayed it is accepted
     */
    public void check_popup() {

        ExtentReports.setChildTest("Check Privacy Popup");

        // Waits till the Privacy block is displayed
        WaitUntil.waitVisible(5, popup);
        Assert.assertTrue(popup.isDisplayed());

        // Accepts popup
        WaitUntil.waitClick(5, pop_accept);
        pop_accept.click();

        // Result reported in Extent report and Logged
        ExtentReports.getChildTest().pass("Privacy popup is displayed and Accepted");
        LoggerConfig.getLogger().info("Privacy popup is displayed and Accepted");

    }

    public void privacy_login() {
        // Setting up Child Test
        ExtentReports.setChildTest("");
    }
}

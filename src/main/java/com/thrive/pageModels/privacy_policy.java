package com.thrive.pageModels;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.thrive.browserSetup.projectSetup;
import com.thrive.modules.actions;
import com.thrive.modules.screenshot;
import com.thrive.reportSetup.extentReports;

public class privacy_policy extends projectSetup {

	// Page Elements
	@FindBy(id = "gdpr-notice-cookie-block")
	private WebElement policyBlock;
	@FindBy(xpath = "//div[@id='gdpr-notice-cookie-block']/div/p/a")
	private WebElement learnMore;
	@FindBy(xpath = "//div[@id='gdpr-notice-cookie-block']/div/div/button/span")
	private WebElement accept;

	/*
	 * Page Constructor Constructor Sets the driver to Current page
	 */
	public privacy_policy(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/*
	 * Check if Privacy Policy popup is displayed if yes then its closed
	 */
	public void policy_check() {

		// Extent Report Child node created
		extentReports.setChildTest("Policy Popup Check");

		com.thrive.modules.wait.waitRefresh(5, policyBlock);
		if (policyBlock.isDisplayed()) {
			actions.moveClick(accept);
			System.out.println("Privacy Policy is Accepted");
			extentReports.getChildTest().info("Privacy Policy is Accepted");
		} else {

			try {
				System.out.println("Privacy Policy is not Accepted");
				extentReports.getChildTest().fail("Privacy Policy is not Accepted",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot.shot()).build());
			} catch (IOException e) {
				e.printStackTrace();
			}

			Assert.assertTrue(false, "Privacy Policy is not Accepted");
		}
	}

}
package com.thrive.pageModels;

import java.io.IOException;

import org.openqa.selenium.By;
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
import com.thrive.utils.excelUtils;

public class login_page extends projectSetup {
	int count;
//	private JavascriptExecutor js = (JavascriptExecutor) driver;
//	private ArrayList<String> al;

	/*
	 * Login page Elements
	 */
	@FindBy(xpath = "//div[@class='block-content']/form/fieldset/div[4]/div/button/span")
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
			extentReports.setChildTest("Error Message Box");

			actions.moveClick(sign_Submit);
//		js.executeScript("arguments[0].scrollIntoView();", sign_Submit);
//		js.executeScript("arguments[0].click();", sign_Submit);

			com.thrive.modules.wait.waitVisible(5, emptyLogErr);

			// Comparing Error message
			Assert.assertEquals(emptyLogErr.getAttribute("innerHTML"), excelUtils.getData("login").get(6));
			System.out.println("Login and Password error message is displayed");
			extentReports.getChildTest().pass("Login and Password error message is displayed");

			count++;
		} catch (Exception e) {
			try {
				System.out.println(e.getCause());
				extentReports.getChildTest().fail(e.getCause(),
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot.shot()).build());
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
		extentReports.setChildTest("Text Field error message");

		// Checking error message displayed in individual input boxes
		if (count == 1) {
			sign_Submit.click();
//			js.executeScript("arguments[0].scrollIntoView();", sign_Submit);
//			js.executeScript("arguments[0].click();", sign_Submit);
		}
		com.thrive.modules.wait.waitVisible(5, email_error);

		// Comparing Error Messages
		Assert.assertEquals(email_error.getText(), excelUtils.findRowData("login").get(5));
		System.out.println("Proper error msg for Email Field is displayed");
		extentReports.getChildTest().pass("Proper error msg for Email Field is displayed");

		Assert.assertEquals(pass_error.getText(), excelUtils.findRowData("login").get(5));
		System.out.println("Proper error msg for Password Field is displayed");
		extentReports.getChildTest().pass("Proper error msg for Password Field is displayed");

	}

	/*
	 * Login with valid credentials Checks the header for username to verify
	 * Successful login
	 */
	public void loginValidCred() throws Exception {

		// Extent Report Child node created
		extentReports.setChildTest("Login with Valid Credentials");

		// Fetching the data from Excel sheet and entering the values
		int cell = 3;
		for (int i = 1; i <= 2; i++) {
			getDriver().findElement(By.xpath("(//form[@class='form form-login']/fieldset/div/div)[" + i + "]/input"))
					.sendKeys(excelUtils.getData("login").get(cell++));
		}

		actions.moveClick(sign_Submit);
//		js.executeScript("arguments[0].scrollIntoView()", sign_Submit);
//		js.executeScript("arguments[0].click();", sign_Submit);

		String fullName = excelUtils.getData("login").get(1) + " " + excelUtils.getData("login").get(2);

		// Waiting for the username to be displayed in the Header
		Thread.sleep(5000);

		// Verifying if the User name is properly displayed
		Assert.assertEquals(userName.getText(), fullName);
		System.out.println("Logged in Successfully");
		extentReports.getChildTest().pass("Logged in Successfully");
	}

	/*
	 * Verifying the Field Error messages for individual fields
	 */
	public void everyFieldErrorCheck() {

		// Extent Report Child node created
		extentReports.setChildTest("Individual Login Field Error Msg");

		// verify the error msg displayed in Password field
		emailBox.sendKeys(excelUtils.getData("login").get(3));
		actions.moveClick(sign_Submit);
//		js.executeScript("arguments[0].click();", sign_Submit);

		// Compare the Error message
		Assert.assertEquals(pass_error.getText(), excelUtils.getData("login").get(5));
		System.out.println("Error message is displayed for Password Field");
		extentReports.getChildTest().pass("Error message is displayed for Password Field");

		emailBox.clear();

		// Verify the error msg displayed in Email field
		passBox.sendKeys(excelUtils.getData("login").get(4));
		actions.moveClick(sign_Submit);
//		js.executeScript("arguments[0].click();", sign_Submit);

		// Compare the Error message
		Assert.assertEquals(email_error.getText(), excelUtils.getData("login").get(5));
		System.out.println("Error message is displayed for Email Field");
		extentReports.getChildTest().pass("Error message is displayed for Email Field");

		passBox.clear();

	}

}
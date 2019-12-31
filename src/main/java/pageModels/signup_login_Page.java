package pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import modules.Assertion;
import modules.Property;
import modules.excelUtils;
import modules.extentReports;
import modules.projectSetup;

public class signup_login_Page extends projectSetup {
	int count;

	/*
	 * Login page Elements
	 */
	@FindBy(xpath = "//div[@class='block-content']/form/fieldset/div[4]/div/button/span")
	private WebElement sign_Submit;
	@FindBy(xpath = "//div[@id='email-error']")
	private WebElement email_error;
	@FindBy(xpath = "//div[@id='pass-error']")
	private WebElement pass_error;
	@FindBy(xpath = "//div[@class='panel header'] //li[@class='greet welcome']/span")
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
	public signup_login_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
//		this.driver = driver;
	}

	/*
	 * Checking error message displayed in Message block
	 */
	public void errorMsgBox() {
		count = 0;

		// Extent Report Child node created
		extentReports.childTest = extentReports.extTest.createNode("Error Message Box");

		sign_Submit.click();

		try {
			modules.wait.waitVisible(5, emptyLogErr);

			// Comparing Error message
			Assertion.assertEquals(emptyLogErr.getAttribute("innerHTML"),
					"A login and a password are required.", extentReports.childTest,
					"Login and Password error message is displayed");
			count++;
		} catch (Exception e) {
			System.out.println("Could Not find the specified Element");
			extentReports.childTest.fail("Could Not find the specified Element");
		}
	}

	/*
	 * Checks if error messages are displayed when fields are empty
	 */
	public void textFieldError() throws Exception {

		// Extent Report Child node created
		extentReports.childTest = extentReports.extTest.createNode("Text Field error message");


		// Checking error message displayed in individual input boxes
		if (count == 1) {
			sign_Submit.click();
		}
		try {
			modules.wait.waitVisible(5, email_error);

			// Comparing Error Messages
			Assertion.assertEquals(email_error.getText(),
					excelUtils.getData(Property.getProperty("sheetName"), 2, 6), extentReports.childTest,
					"Proper error msg for Email Field is displayed");

			Assertion.assertEquals(pass_error.getText(),
					excelUtils.getData(Property.getProperty("sheetName"), 2, 6), extentReports.childTest,
					"Proper error msg for Password Field is displayed");
		} catch (Exception e) {
			System.out.println("Could Not find the specified Element");
			extentReports.childTest.fail("Could Not find the specified Element");
		}
	}

	/*
	 * Login with valid credentials Checks the header for username to verify
	 * Successful login
	 */
	public void loginValidCred() throws Exception {

		// Extent Report Child node created
		extentReports.childTest = extentReports.extTest.createNode("Login with Valid Credentials");

		// Fetching the data from Excel sheet and entering the values
		int cell = 4;
		for (int i = 1; i <= 2; i++) {
			driver.findElement(By.xpath("(//form[@class='form form-login']/fieldset/div/div)[" + i + "]/input"))
					.sendKeys(excelUtils.getData("userData", 2, cell++));
		}

		sign_Submit.click();

		String fullName = excelUtils.getData(Property.getProperty("sheetName"), 2, 2) + " "
				+ excelUtils.getData(Property.getProperty("sheetName"), 2, 3);

		// Waiting for the username to be displayed in the Header
		Thread.sleep(5000);
		
		// Splitting the text from Default text
		String[] acName = userName.getText().split(",");

		// Verifying if the User name is properly displayed
		Assertion.assertContains(acName[1].trim().substring(0, acName[1].trim().length() - 1), fullName,
				extentReports.childTest, "Logged in Successfully");
	}

}

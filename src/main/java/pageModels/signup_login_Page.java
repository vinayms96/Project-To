package pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import modules.Assertion;
import modules.Property;
import modules.excelUtils;
import modules.extentReports;
import modules.projectSetup;

public class signup_login_Page extends projectSetup {
	int count;
	WebDriver driver;
	ExtentTest childTest;

	/*
	 * Homepage Elements
	 */

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
		this.driver = driver;
	}

	/*
	 * Checking error message displayed in Message block
	 */
	public void errorMsgBox() {
		count=0;
		/*
		 * Extent Report Child node created
		 */
		childTest = extentReports.extTest.createNode("errorMsgBox");

		sign_Submit.click();
		try {
			modules.wait.waitVisible(driver, emptyLogErr);

			/*
			 * Comparing Error message
			 */
			Assertion.assertEquals(driver, emptyLogErr.getAttribute("innerHTML"),
					"A login and a password are required.", childTest, "Login and Password error message is displayed",
					"Login and Password error message is not displayed");
			count++;
		} catch (Exception wait) {
			System.out.println("'A login and a password are required' error msg is NOT displayed");
			childTest.info("'A login and a password are required' error msg is NOT displayed");
		}

	}

	/*
	 * Checks if error messages are displayed when fields are empty
	 */
	public void textFieldError() throws Exception {
		/*
		 * Extent Report Child node created
		 */
		childTest = extentReports.extTest.createNode("textFieldError");

		/*
		 * Checking error message displayed in individual input boxes
		 */
		if(count==1) {
			sign_Submit.click();
		}
		modules.wait.waitVisible(driver, email_error);

		// Comparing Error Messages
		Assertion.assertEquals(driver, email_error.getText(),
				excelUtils.getData(Property.getProperty("sheetName"), 2, 6), childTest,
				"Proper error msg for Email Field is displayed", "Proper error msg for Email Field is NOT displayed");
		Assertion.assertEquals(driver, pass_error.getText(),
				excelUtils.getData(Property.getProperty("sheetName"), 2, 6), childTest,
				"Proper error msg for Password Field is displayed",
				"Proper error msg for Password Field is NOT displayed");
	}

	/*
	 * Login with valid credentials Checks the header for username to verify
	 * Successful login
	 */
	public void signin() throws Exception {
		/*
		 * Extent Report Child node created
		 */
		childTest = extentReports.extTest.createNode("signin");

		int cell = 4;
		for (int i = 1; i <= 2; i++) {
			driver.findElement(By.xpath("(//form[@class='form form-login']/fieldset/div/div)[" + i + "]/input"))
					.sendKeys(excelUtils.getData("userData", 2, cell++));
		}

		sign_Submit.click();
		Thread.sleep(10000);

		String fullName = excelUtils.getData(Property.getProperty("sheetName"), 2, 2) + " "
				+ excelUtils.getData(Property.getProperty("sheetName"), 2, 3);
		String[] acName = userName.getText().split(",");

		Assertion.assertContains(driver, acName[1].trim().substring(0, acName[1].trim().length() - 1), fullName,
				childTest, "Logged in Successfully", "User could NOT login");
	}

}

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
import modules.openBrowser;

public class signup_login_Page extends openBrowser {
	ExtentTest extTest;

	/*
	 * Login page Web Elements
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
	}

	/*
	 * Checks if error messages are displayed when fields are empty
	 */
	public void checkError() throws Exception {
		extTest = extentReports.extentTest();

		/*
		 * Checking error message displayed in Message block
		 */

//		sign_Submit.click();
//		modules.wait.fluentVisible(emptyLogErr);
//		modules.wait.waitVisible(emptyLogErr);

		// Comparing Error message
//		Assertion.assertEquals(emptyLogErr.getAttribute("innerHTML"), "A login and a password are required.",
//				extTest,
//				"Login and Password error message is displayed", "Login and Password error message is not displayed");
//		driver.navigate().refresh();

		/*
		 * Checking error message displayed in individual input boxes
		 */
//		Thread.sleep(2000);
		sign_Submit.click();
		modules.wait.waitVisible(email_error);

		// Comparing Error Messages
		Assertion.assertEquals(email_error.getText(), excelUtils.getData(Property.getProperty("sheetName"), 2, 6),
				extTest, "Proper error msg for 'Email Field' is displayed",
				"Proper error msg for 'Email Field' is NOT displayed");
		Assertion.assertEquals(pass_error.getText(), excelUtils.getData(Property.getProperty("sheetName"), 2, 6),
				extTest, "Proper error msg for 'Password Field' is displayed",
				"Proper error msg for 'Password Field' is NOT displayed");

	}

	/*
	 * Login with valid credentials Checks the header for username to verify
	 * Successful login
	 */
	public void signin() throws Exception {
		extTest = extentReports.extentTest();
		
		int cell = 4;
		for (int i = 1; i <= 2; i++) {
			driver.findElement(By.xpath("(//form[@class='form form-login']/fieldset/div/div)[" + i + "]/input"))
					.sendKeys(excelUtils.getData("userData", 2, cell++));
		}
//		actions.moveClick(sign_Submit);
		sign_Submit.click();
		Thread.sleep(10000);
//		modules.wait.fluentVisible(userName);
		String fullName = excelUtils.getData(Property.getProperty("sheetName"), 2, 2) + " "
				+ excelUtils.getData(Property.getProperty("sheetName"), 2, 3);
		String[] acName = userName.getText().split(",");
		Assertion.assertContains(acName[1].trim().substring(0, acName[1].trim().length() - 1), fullName, extTest,
				"Logged in Successfully", "User could NOT login");
	}

}

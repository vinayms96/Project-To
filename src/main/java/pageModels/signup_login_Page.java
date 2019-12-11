package pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import modules.Assertion;
import modules.Property;
import modules.actions;
import modules.excelUtils;
import modules.openBrowser;

public class signup_login_Page extends openBrowser {

	/*
	 * Login page Web Elements
	 */
	@FindBy(xpath = "//div[@class='block-content']/form/fieldset/div[4]/div/button/span")
	private WebElement sign_Submit;
	@FindBy(xpath = "//div[@id='email-error']")
	private WebElement email_error;
	@FindBy(xpath = "//div[@id='pass-error']")
	private WebElement pass_error;

	public signup_login_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void checkError(ExtentTest extTest) {
		actions.moveClick(sign_Submit);
		modules.wait.waitVisible(email_error);
		modules.wait.waitVisible(pass_error);
		Assertion.assertEquals(email_error.getText(), excelUtils.getData(Property.getProperty("sheetName"), 2, 6),
				extTest, "Proper error msg is displayed", "Proper error msg is NOT displayed");
	}

	public void signin(ExtentTest extTest) {
		int cell = 4;
		for (int i = 1; i <= 2; i++) {
			driver.findElement(By.xpath("(//form[@class='form form-login']/fieldset/div/div)[" + i + "]/input"))
					.sendKeys(excelUtils.getData("userData", 2, cell++));
		}
		actions.moveClick(sign_Submit);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

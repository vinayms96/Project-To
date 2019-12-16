package pageModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import modules.actions;
import modules.extentReports;

public class homePage {
	ExtentTest extTest;

	@FindBy(xpath = "//nav[@class='navigation']/ul/li[2]")
	private WebElement menu;
	@FindBy(xpath = "//ul[@class=\"header links\"]/li[2]/a")
	private WebElement login;

	public homePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void loginLink() throws Exception {
		actions.moveClick(login);
		extentReports.extentTest().info("SignIn Link is clicked");
	}

}

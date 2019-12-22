package pageModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import modules.actions;
import modules.projectSetup;

public class homePage extends projectSetup{
	WebDriver driver;
	ExtentTest childTest;

	@FindBy(xpath = "//nav[@class='navigation']/ul/li[2]")
	private WebElement menu;
	@FindBy(xpath = "//ul[@class=\"header links\"]/li[2]/a")
	private WebElement login;

	public homePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void loginLink() throws Exception {	
		System.out.println(driver);
		actions.moveClick(driver,login);
	}

}

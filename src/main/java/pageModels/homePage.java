package pageModels;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import modules.Assertion;
import modules.actions;
import modules.extentReports;
import modules.projectSetup;

public class homePage extends projectSetup {

	@FindBy(xpath = "//nav[@class='navigation']/ul/li[2]")
	private WebElement menu;
	@FindBy(xpath = "//ul[@class=\"header links\"]/li[2]/a")
	private WebElement login;
	@FindBy(xpath = "//div[@id='store.menu']/nav/ul/li")
	private List<WebElement> menuLinks;
	@FindBy(xpath = "//div[@class='breadcrumbs']/ul/li[2]/strong")
	private WebElement breadcrumbs;
	@FindBy(xpath = "//div[@class='page-title-wrapper']/h1/span")
	private WebElement pageTitle;

	public homePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/*
	 * Click on Login link in the Header
	 */
	public void clickLoginLink() throws Exception {
		extentReports.childTest = extentReports.extTest.createNode("Click LoginLink");
		actions.moveClick(login);
		extentReports.childTest.info("Login link is clicked in Homepage");
	}

	/*
	 * Click on Menu links and check if they are redirecting to correct pages
	 */
	public void checkMenuLinks() {

		// Created a child node
		extentReports.childTest = extentReports.extTest.createNode("Check All the Menu Links");

		// Looping through all the Menu links and verifying the redirection 
		for (int link = 1; link <= menuLinks.size(); link++) {
			actions.moveClick(driver.findElement(By.xpath("(//div[@id='store.menu']/nav/ul/li)[" + link + "]")));
			modules.wait.waitVisible(5, breadcrumbs);
			String breadName = breadcrumbs.getText();
			Assertion.assertEquals(pageTitle.getText(), breadName, extentReports.childTest,
					"Menu link is redirected to " + breadName + " page",
					"Menu link is NOT redirected to " + breadName + " page");
			
			// Navigating to home page only in firefox browser
			if(projectSetup.extBrowser.equalsIgnoreCase("Firefox")) {
				driver.navigate().back();
			}
		}
	}

}

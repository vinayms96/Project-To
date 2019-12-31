package pageModels;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import modules.Assertion;
import modules.actions;
import modules.extentReports;
import modules.projectSetup;

public class homePage extends projectSetup {

	@FindBy(xpath = "//div[@class='panel header']/ul/li[2]/a")
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

		// Open all Menu links in different Tabs
				Iterator<WebElement> links = menuLinks.iterator();
				while (links.hasNext()) {
					WebElement element = links.next();
					actions.clickOpenTab(element);
				}
				// Switch to all the tabs and verify the Pages
				Set<String> windows = driver.getWindowHandles();
				Iterator<String> wind = windows.iterator();
				String parent = wind.next();
				while(wind.hasNext()) {
					driver.switchTo().window(wind.next());
					String breadName = breadcrumbs.getText();
					Assertion.assertEquals(pageTitle.getText(), breadName, extentReports.childTest,
							"Menu link is redirected to " + breadName + " page");
					driver.close();
				}
				driver.switchTo().window(parent);
				
		// Looping through all the Menu links and verifying the redirection 
//		for (int link = 1; link <= menuLinks.size(); link++) {
//			actions.moveClick(driver.findElement(By.xpath("(//div[@id='store.menu']/nav/ul/li)[" + link + "]")));
//			modules.wait.waitVisible(5, breadcrumbs);
//			String breadName = breadcrumbs.getText();
//			Assertion.assertEquals(pageTitle.getText(), breadName, extentReports.childTest,
//					"Menu link is redirected to " + breadName + " page");
	
//			// Navigating to home page only in firefox browser
//			if(projectSetup.extBrowser.equalsIgnoreCase("Firefox")) {
//				driver.navigate().back();
//			}
//		}
	}

}

package com.thrive.pageModels;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.thrive.browserSetup.ProjectSetup;
import com.thrive.modules.actions;
import com.thrive.reportSetup.extentReports;

public class home_page extends ProjectSetup {

	@FindBy(xpath = "//div[@class='header content']/div[2]/a")
	private WebElement menu;
	@FindBy(xpath = "//ul[@class=\"header links\"]/li[2]/a")
	private WebElement login;
//	@FindBy(xpath = "//div[@id='store.menu']/nav/ul/li")
//	private List<WebElement> menuLinks;
	@FindBy(xpath = "//div[@id='store.menu']/nav/div/div/div")
	private List<WebElement> menuLinks;
	@FindBy(xpath = "//div[@class='breadcrumbs']/ul/li[2]/strong")
	private WebElement breadcrumbs;
	@FindBy(xpath = "//div[@class='page-title-wrapper']/h1/span")
	private WebElement pageTitle;

	public home_page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/*
	 * Click on Login link in the Header
	 */
	public void clickLoginLink() throws InvocationTargetException {
		extentReports.setChildTest("Click LoginLink");		
		
//		actions.moveClick(menu);
		System.out.println("menu=" + getDriver().findElement(By.xpath("//div[@class='header content']/div[2]/a")));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();	
		js.executeScript("arguments[0].click();", menu);
				
		extentReports.getChildTest().info("Login link is clicked in Homepage");
	}

	/*
	 * Click on Menu links and check if they are redirecting to correct pages
	 */
	public void checkMenuLinks() {

		// Created a child node
		extentReports.setChildTest("Check All the Menu Links");

		// Open all Menu links in different Tabs
				Iterator<WebElement> links = menuLinks.iterator();
				while (links.hasNext()) {
					WebElement element = links.next().findElement(By.tagName("a"));
					actions.clickOpenTab(element);
				}
				// Switch to all the tabs and verify the Pages
				Set<String> windows = getDriver().getWindowHandles();
				Iterator<String> wind = windows.iterator();
				String parent = wind.next();
				while(wind.hasNext()) {
					getDriver().switchTo().window(wind.next());
					String breadName = breadcrumbs.getText();
//					Assertion.assertEquals(pageTitle.getText(), breadName, extentReports.childTest,
//							"Menu link is redirected to " + breadName + " page");
					Assert.assertEquals(pageTitle.getText(), breadName);
					extentReports.getChildTest().pass("Menu link is redirected to " + breadName + " page");
					
					getDriver().close();
				}
				getDriver().switchTo().window(parent);
				
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
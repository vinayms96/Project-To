package testCases;

import org.testng.annotations.Test;

import modules.openBrowser;
import pageModels.homePage;

public class testClass extends openBrowser{
	
	@Test(groups = {"exe.home"})
	public void base() throws Exception {
		
		homePage hp = new homePage(driver);
		hp.hover_menu();
		
	}
	
}

package pageModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import modules.actions;
import modules.wait;

public class homePage {
	
	@FindBy(xpath = "//nav[@class='navigation']/ul/li[2]")
	private WebElement menu;
	
	public homePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void hover_menu() throws Exception {
		wait.waitVisible(menu);
		actions.moveOver(menu);
		Thread.sleep(4000);
	}

}

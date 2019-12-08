package pageModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {
	
	@FindBy(xpath = "//nav[@class='navigation']/ul/li[2]")
	private WebElement menu;
	@FindBy(xpath = "//input[@id='search']")
	private WebElement entertext;
	
	public homePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void hover_menu() throws Exception {
		Thread.sleep(2000);
		entertext.sendKeys("Hey");
	}

}

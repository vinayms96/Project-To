package modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class actions extends projectSetup{
	static Actions a;
	
	public static void moveOver(WebDriver driver, WebElement element) {
		a = new Actions(driver);
		a.moveToElement(element).build().perform();
	}
	
	public static void moveClick(WebDriver driver, WebElement element) {
		a = new Actions(driver);
		a.moveToElement(element).click().build().perform();
	}

}
